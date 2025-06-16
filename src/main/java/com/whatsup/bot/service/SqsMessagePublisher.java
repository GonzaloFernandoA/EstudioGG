package com.whatsup.bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
public class SqsMessagePublisher {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.respond_turnos}")
    private String webhookMessagesQueueUrl;

    public SqsMessagePublisher(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    public void sendMessage(String messageBody) {
        SendMessageRequest request = SendMessageRequest.builder()
                .queueUrl(webhookMessagesQueueUrl)
                .messageBody(messageBody)
                .build();

        sqsClient.sendMessage(request);
    }


        // For now, we will just print the message to the console
}

