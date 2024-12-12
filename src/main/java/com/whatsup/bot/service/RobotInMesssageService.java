/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.task.respuestaHorasTask;
import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class RobotInMesssageService {

    private static final Logger logger = LoggerFactory.getLogger(RobotInMesssageService.class);

    @Autowired
    respuestaHorasTask respuestaHoras ;
    
    @Autowired
    ReservaService reserva;
    
    @Autowired
    ContactConfig config;

    @Autowired
    EquivalenciaService equivalencia;

    @Autowired
    private EventService event;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void SaveInconmeMessage(String incomingMessage) {

        Root message;
        if (incomingMessage.contains("Asesoramiento Virtual")) {

            try {
                message = objectMapper.readValue(incomingMessage, Root.class);
                String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                String telefono = equivalencia.get(telefonowa_id);
                event.saveEvent(telefono, "Llamar ahora. " + telefono);
                event.saveOutMessage(telefono, "Muchas Gracias. Nos comunicaremos con usted a la brevedad.");
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        } else if (incomingMessage.contains("Agendar Turno")) {
            try {
                message = objectMapper.readValue(incomingMessage, Root.class);
                String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                String telefono = equivalencia.get(telefonowa_id);
                
                event.saveEvent(telefono, "Envio agenda dia.");
                event.saveOutMessage(telefono, "MENU_DIA");
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
        } else if (incomingMessage.contains("\"type\":\"text\"")) {
                respuestaHoras.Run(incomingMessage);
        }
    }

    public void SaveInconmeMessage(ResponseRoot incomingMessage) {

        logger.info("SaveInconmeMessage:" + incomingMessage);
        String wa_id = incomingMessage.contacts.get(0).wa_id;
        String telefono = incomingMessage.contacts.get(0).input;
        logger.info("SaveInconmeMessage wa_ip " + wa_id);
        logger.info("SaveInconmeMessage telefono " + telefono);
        equivalencia.save(wa_id, telefono);
    }

    /**
     * @param event the event to set
     */
    public void setEvent(EventService event) {
        this.event = event;
    }

    public void setEquivalencia(EquivalenciaService equivalenciaService) {
        this.equivalencia = equivalenciaService;
    }
}
