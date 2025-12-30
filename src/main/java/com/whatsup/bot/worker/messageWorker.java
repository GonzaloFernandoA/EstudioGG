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
import com.whatsup.bot.service.*;

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
    ConversationService conversationService;

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
            service.enviar(request);

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

        service.sendMessage(telefono, contenido);
    }
}
