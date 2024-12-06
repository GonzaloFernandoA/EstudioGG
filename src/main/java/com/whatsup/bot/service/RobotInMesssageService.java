/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    ContactConfig config;

    @Autowired
    EquivalenciaService equivalencia;

    @Autowired
    EventService event;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void SaveInconmeMessage(String incomingMessage) {
        if (incomingMessage.contains("Asesoramiento Virtual")) {
            Root message;
            try {
                message = objectMapper.readValue(incomingMessage, Root.class);
                String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                String telefono = equivalencia.get(telefonowa_id);
                
                event.saveEvent(telefono , "Llamar ahora. " + telefono );
                event.saveOutMessage(telefono, "Muchas Gracias. Nos comunicaremos con usted a la brevedad.");
            } catch (IOException ex) {
                logger.error(ex.getMessage());
            } 
        }
    }

    public void SaveInconmeMessage(ResponseRoot incomingMessage) {

        logger.info("SaveInconmeMessage:" + incomingMessage);
        String wa_id = incomingMessage.contacts.get(0).wa_id;
        String telefono = incomingMessage.contacts.get(0).input;
        logger.info("SaveInconmeMessage wa_ip " + wa_id ) ;
        logger.info("SaveInconmeMessage telefono " + telefono ) ;
        equivalencia.save(wa_id, telefono);
     //   event.saveEvent(telefono, "Llamar ahora. " + telefono);
     //   event.saveOutMessage(config.out + telefono, "Muchas Gracias. Nos comunicaremos con usted a la brevedad.");
    }

}
