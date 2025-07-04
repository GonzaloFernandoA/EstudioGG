/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.worker;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.messageBuilder;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.service.ContactService;

import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.WhatsAppService;
import com.whatsup.bot.service.trackingService;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class messageWorker {

    private final Logger log = LoggerFactory.getLogger(messageWorker.class);
    @Autowired
    trackingService tracking;

    @Autowired
    CarpetasConfig config;

    @Autowired
    WhatsAppService service;

    @Autowired
    ContactService contact; 
    
    @Autowired
    EventService eventService;

    @Autowired
    messageBuilder builder;

    @Autowired
    ReservaService reserva;

    private boolean isTemplateJson(String contenido) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readTree(contenido);
            return node.has("type") && "template".equals(node.get("type").asText());
        } catch (Exception e) {
            return false;
        }
    }

    private void handleTemplate(String contenido) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            MessageTemplateRequest request = mapper.readValue(contenido, MessageTemplateRequest.class);
            service.enviar(request) ;

        } catch (Exception e) {
            log.error("Error parsing contenido to MessageTemplateRequest", e);
        }
    }

    public void ejecutarTarea(String telefono, String contenido) {

        log.info("Enviando mensaje a  " + telefono);


        if (isTemplateJson(contenido)) {
            handleTemplate(contenido);
            return;
        }

        if (contenido.contains("ENVIAR_ENCUESTA")) {
            service.enviarMensajeTemplate(telefono, contact.getName(telefono));
            eventService.saveEvent(telefono, "ENCUESTA_ENVIADA");

        } else {
            if (contenido.contains("MENU_DIA")) {
                List<String> dias = reserva.getDiasDisponibles();
                String message = builder.AgendaBuild(dias);
                service.sendMessage(telefono, "Elija un día para que nos comuniquemos con usted (indíque la letra) :" + System.lineSeparator() + "\u2B07"
                        + System.lineSeparator() + message);
                eventService.saveEvent(telefono, "DIAS ENVIADOS");
                tracking.saveFechasEnviadas(telefono, dias);

            } else if (contenido.contains("MENU_HORA")) {
                log.info(contenido);
                log.info(contenido.replaceAll("MENU_HORA_", ""));
                List<String> opciones = reserva.getTurnosLibres(tracking.get(telefono).getFechaReservada());
                String message = builder.AgendaBuildHoras(opciones);
                service.sendMessage(telefono, "Elija una hora para que nos comuniquemos con usted (indíque la letra) :" + System.lineSeparator() + "\u2B07"
                        + System.lineSeparator() + message);
                eventService.saveEvent(telefono, "HORARIOS ENVIADOS");
                tracking.saveHorasEnviadas(telefono, opciones);
            } else if (contenido.contains("CONFIRMACION_TURNO")) {
                String message = builder.ConfirmacionMessage(telefono);
                service.sendMessage(telefono, message);
                eventService.saveEvent(telefono, "CONFIRMANDO TURNO.");
            } else {
                service.sendMessage(telefono, contenido);
            }
        }

    }

}
