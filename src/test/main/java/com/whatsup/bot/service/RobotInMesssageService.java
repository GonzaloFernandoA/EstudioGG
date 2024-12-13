/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.message.response.Root;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class RobotInMesssageService {

    @Autowired
    ContactConfig config;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void SaveInconmeMessage(String incomingMessage) {
        if (incomingMessage.contains("Asesoramiento Virtual")) {
            Root message;
            try {
                message = objectMapper.readValue(incomingMessage, Root.class);
                String telefono = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
                File file = new File(config.eventsPath + telefono );
                objectMapper.writeValue(file, "Llamar ahora. " + telefono);

                File outfile = new File(config.out + telefono );
                objectMapper.writeValue(outfile, "Muchas Gracias. Nos comunicaremos con usted a la brevedad.");

            } catch (JsonProcessingException ex) {
                Logger.getLogger(RobotInMesssageService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RobotInMesssageService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
