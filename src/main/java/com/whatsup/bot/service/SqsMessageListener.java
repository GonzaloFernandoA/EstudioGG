/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;


import io.awspring.cloud.sqs.annotation.SqsListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.utils.TransformMessage;
import com.whatsup.bot.worker.messageWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SqsMessageListener {

    @Autowired
    RobotInMesssageService robot; 
    
    @Autowired
    TransformMessage getKeyComponent; 
    
    @Autowired
    OutService outService; 
    
    @Autowired
    messageWorker worker; 
    
    private static final Logger logger = LoggerFactory.getLogger(SqsMessageListener.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SqsListener("https://sqs.us-east-2.amazonaws.com/034362075244/WebhookMessges")
    public void receiveMessage(String message) {
        try {
          
            logger.info("Mensaje recibido WebhookMessges:" + message);
            robot.SaveInconmeMessage(message);

        } catch (Exception e) {
            logger.error("Error al procesar el mensaje: " + e.getMessage());
        }
    }

    @SqsListener("https://sqs.us-east-2.amazonaws.com/034362075244/S3ToWorker")
    public void receiveMessageToSend(String message) {
        try {
          
            logger.info("Mensaje recibido S3ToWorker:" + message);
            String file = getKeyComponent.transform(message);
            logger.info("Mensaje recibido File Name:" + file);
            OutMessage fileMessage = outService.get(file);
            logger.info("Mensaje recibido File Name:" + fileMessage.getTelefono() + " Contenido: "+ fileMessage.getContenido());
            worker.ejecutarTarea(fileMessage.getTelefono(), fileMessage.getContenido());
            
           

        } catch (Exception e) {
            logger.error("Error al procesar el mensaje: " + e.getMessage());
        }
    }
    
    
}

