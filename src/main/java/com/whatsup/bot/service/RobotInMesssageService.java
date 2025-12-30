/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.ExecutorParameterBuilder;
import com.whatsup.bot.builder.task.ResponseBuilder;
import com.whatsup.bot.builder.task.TaskBotExecutor;
import com.whatsup.bot.builder.task.respuestaHorasTask;
import com.whatsup.bot.message.Metadata;
import com.whatsup.bot.message.WebHookResponse.WebHookMessageWIthSender;
import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.message.responsePost.ResponseRoot;

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
    ExecutorParameterBuilder builder;

    @Autowired
    ResponseBuilder responseBuilder;

    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

/*    @Autowired
    respuestaHorasTask respuestaHoras; */

    @Autowired
    ConversationService conversationService;

    @Autowired
    EquivalenciaService equivalencia;

    @Autowired
    private EventService event;

    @Autowired
    LogMensajesService logMensajesService;

    @Autowired
    TaskBotExecutor executor;

    @Autowired
    ContactService contactService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void SaveInconmeMessage(String incomingMessage)
    {
        Root message;
        WebHookMessageWIthSender messageWithSender;

        if (incomingMessage.contains("Unknown")) {
            logger.info("System Message recibido: " + incomingMessage);
            return;
        }

        try {
            messageWithSender = objectMapper.readValue(incomingMessage, WebHookMessageWIthSender.class);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            return;
        }

        String telefono = equivalencia.get(messageWithSender.getSender());
        String name = contactService.getName(telefono);
        String mensajeRecibido = messageWithSender.getText();
        String lastAction = conversationService.get(telefono).getLastMessage();

        logger.info("lastAction {} Mensaje recibido: {} -telefono {} -name {}",lastAction, mensajeRecibido, telefono, name);
        // executor.runAllTasks(lastAction, mensajeRecibido, name, telefono);

        executor.runAllTasks(builder.Build(incomingMessage));
        logger.info("Fin de las tareas del Bot");

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
