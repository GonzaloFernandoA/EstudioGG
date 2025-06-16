/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.utils.TransformMessage;
import com.whatsup.bot.worker.messageWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;

import java.util.List;

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

    private final SqsClient sqsClient;

    @Value("${aws.sqs.webhook-messages}")
    private String webhookMessagesQueue;

    @Value("${aws.sqs.s3-to-worker}")
    private String s3ToWorkerQueue;

    public SqsMessageListener(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }
    
    private static final Logger logger = LoggerFactory.getLogger(SqsMessageListener.class);
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Scheduled(fixedDelay = 10000) // cada 30 segundos después de terminar
    public  void Process()
    {
        this.processWebhookMessages();
        this.processS3ToWorker();
    }


    public void processWebhookMessages() {
        Message message = read(webhookMessagesQueue);
        if (message != null) {
            try {
                String body = message.body();
                logger.info("Mensaje recibido WebhookMessages: " + body);
                robot.SaveInconmeMessage(body);
            } catch (Exception e) {
                logger.error("Error al procesar el mensaje: " + e.getMessage());
            } finally {
                deleteMessage(webhookMessagesQueue, message);
            }
        }
    }

    public void processS3ToWorker() {
        Message message = read(s3ToWorkerQueue);
        if (message != null) {
            try {
                String body = message.body();
                logger.info("Mensaje recibido S3ToWorker: " + body);
                String file = getKeyComponent.transform(body);
                logger.info("Mensaje recibido File Name: " + file);
                OutMessage fileMessage = outService.get(file);
                logger.info("Mensaje recibido File Name: " + fileMessage.getTelefono() + " Contenido: " + fileMessage.getContenido());
                worker.ejecutarTarea(fileMessage.getTelefono(), fileMessage.getContenido());
            } catch (Exception e) {
                logger.error("Error al procesar el mensaje: " + e.getMessage());
            } finally {
                deleteMessage(s3ToWorkerQueue, message);
            }
        }
    }

    public Message read(String queueUrl) {
        ReceiveMessageRequest request = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)  // ✅ Solo 1 mensaje
                .waitTimeSeconds(1)
                .build();

        ReceiveMessageResponse response = sqsClient.receiveMessage(request);
        List<Message> messages = response.messages();

        if (messages.isEmpty()) {
            logger.info("No se encontraron mensajes en la cola: {}", queueUrl);
            return null;
        } else {
            return messages.get(0);
        }
    }

    public void deleteMessage(String queueUrl, Message receiptHandle) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(receiptHandle.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteRequest);
        logger.info("Mensaje eliminado de la cola: {}", queueUrl);
    }
}

