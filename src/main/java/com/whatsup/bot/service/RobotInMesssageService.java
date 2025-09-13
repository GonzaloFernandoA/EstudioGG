/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.task.ResponseBuilder;
import com.whatsup.bot.builder.task.respuestaHorasTask;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.message.Metadata;
import com.whatsup.bot.message.SqsMessageTurno;
import com.whatsup.bot.message.WebHookResponse.WebHookMessageWIthSender;
import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.message.responsePost.ResponseRoot;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gonzalo_Avalos
 */
@Service
public class RobotInMesssageService {

    private static final Logger logger = LoggerFactory.getLogger(RobotInMesssageService.class);

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

    @Autowired
    respuestaHorasTask respuestaHoras;

    @Autowired
    ConversationService conversationService;

    @Autowired
    EquivalenciaService equivalencia;

    @Autowired
    private EventService event;

    @Autowired
    LogMensajesService logMensajesService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void SaveInconmeMessage(String incomingMessage) {
        Root message;
        WebHookMessageWIthSender messageWithSender;

        if (incomingMessage.contains("Unknown"))
        {
            logger.info("System Message recibido: " + incomingMessage);
            return;
        }

        try {
            messageWithSender = objectMapper.readValue(incomingMessage, WebHookMessageWIthSender.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return;
        }


        if (conversationService.get(equivalencia.get(messageWithSender.getSender())).getLastMessage().equals("MENSAJE_WELCOME_ENVIADO")) {
            String telefono = equivalencia.get(messageWithSender.getSender());
            event.saveOutMessage(telefono, "ENVIAR_ENCUESTA");
            conversationService.save(telefono, "ENVIAR_ENCUESTA");
            return ;
        }

        if (conversationService.get(equivalencia.get(messageWithSender.getSender())).getLastMessage().equals("MENSAJE_ENCUESTA_ENVIADO")
        && messageWithSender.text.equals("2")
        )
        {
            try {
                message = objectMapper.readValue(incomingMessage, Root.class);
                String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                String telefono = equivalencia.get(telefonowa_id);
                event.saveEvent(telefono, "Llamar ahora. " + telefono);
                event.saveOutMessage(telefono, "Muchas Gracias. Nos comunicaremos con usted a la brevedad.");
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            }
            return ;
        }

        if (conversationService.get(equivalencia.get(messageWithSender.getSender())).getLastMessage().equals("MENSAJE_ENCUESTA_ENVIADO")
                && messageWithSender.text.equals("1")
        )
        {
                try {
                    message = objectMapper.readValue(incomingMessage, Root.class);
                    String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                    String telefono = equivalencia.get(telefonowa_id);

                    event.saveEvent(telefono, "Envio agenda dia.");
                    event.saveOutMessage(telefono, "MENU_DIA");
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }

            return ;
            }

         if (incomingMessage.contains("\"type\": \"text\"")) {
            logger.info("type text: " + incomingMessage);
            respuestaHoras.Run(incomingMessage);
        } else {
            Root messageResponse = responseBuilder.Build(incomingMessage);
            LogMensajes logMensaje = logMensajesService.get(messageResponse.entry.get(0).changes.get(0).
                    value.messages.get(0).context.id);

            String valueResponse = messageResponse.entry.get(0).changes.get(0).value.messages.get(0).button.payload;
            logger.info("Mensaje recibido: {} - {} - {}", logMensaje.getCustom_id(), logMensaje.getId(), valueResponse);
            SqsMessageTurno messageTurno = new SqsMessageTurno(logMensaje, valueResponse);
            try {
                String jsonMessage = objectMapper.writeValueAsString(messageTurno);
                logger.info("Mensaje reenviado a turnos: " + jsonMessage);
                sqsMessagePublisher.sendMessage(jsonMessage);
            } catch (JsonProcessingException e) {
                logger.error("Error al convertir el mensaje a JSON: " + e.getMessage());
            }
        }
    }

    public void SaveInconmeMessage(ResponseRoot incomingMessage) {

        String wa_id = incomingMessage.contacts.get(0).wa_id;
        String telefono = incomingMessage.contacts.get(0).input;
        logger.info("SaveInconmeMessage wa_ip " + wa_id);
        logger.info("SaveInconmeMessage telefono " + telefono);
        equivalencia.save(wa_id, telefono);
    }

    public void SaveWa_id(ResponseRoot incomingMessage, Map<String, Object> mensaje) {

        String waMessage_id = incomingMessage.messages.get(0).id;
        logger.info("SaveInconmeMessage wa_ip " + waMessage_id);
        logMensajesService.save(waMessage_id, (Metadata) mensaje.get("metadata"));
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
