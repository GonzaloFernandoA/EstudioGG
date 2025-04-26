/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.S3.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class TransformMessage {

    private static final Logger logger = LoggerFactory.getLogger(TransformMessage.class);

    public String transform(String body) {
        
        ObjectMapper mapper = new ObjectMapper();
        Root root = null;
        try {
            root = mapper.readValue(body, Root.class);
        } catch (JsonProcessingException ex) {
            logger.error(ex.getMessage());
        }

        String key = root.records.get(0).s3.object.key;

        return key;
    }
}
