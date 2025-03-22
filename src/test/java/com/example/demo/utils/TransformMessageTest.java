/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.utils;

import com.example.demo.MockingBaseTestClass;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.utils.TransformMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;


/**
 *
 * @author Gonzalo_Avalos
 */
public class TransformMessageTest extends MockingBaseTestClass{
    
    @InjectMocks
    TransformMessage transformer; 
    
    @Test
    public void transformTest()
    {
        
       String response = null;
        try {
            response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/Events/S3EventMessage.json")));
        } catch (IOException ex) {
        }
        String message = transformer.transform(response);
        Assertions.assertEquals("out/telefono.json", message);
    }
    
}
