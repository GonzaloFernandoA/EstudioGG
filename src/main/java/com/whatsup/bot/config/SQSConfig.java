/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.config;

//@Configuration
public class SQSConfig  {
    
/*    private static final String QUEUE_URL = "https://sqs.us-east-2.amazonaws.com/034362075244/S3ToWorker";
    
    @Bean
    public AmazonSQS amazonSQS() {
        // Reemplaza con tus credenciales de AWS
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
                this.getAccessKeyId(), 
                this.getSecretAccessKey()
        );

        return AmazonSQSClientBuilder.standard()
                .withRegion("us-east-1") // Cambia la región según corresponda
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
    
    @Bean
    public ReceiveMessageRequest getRequest()
    {
               return new ReceiveMessageRequest()
                .withQueueUrl(QUEUE_URL)
                .withMaxNumberOfMessages(1) // Leer un mensaje a la vez
                .withWaitTimeSeconds(10);  // Long polling para reducir el costo
    }
    
    @Bean
    public ReceiveMessageResult getResult()
    {
        return new ReceiveMessageResult();
    }
    
    @Bean
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder.standard()
                .withRegion("us-east-2")  // Cambia a tu región de AWS
                .build();
    }*/
}