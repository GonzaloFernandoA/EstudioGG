/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.example.demo.MockingBaseTestClass;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.service.OutMessageProcesorService;
import com.whatsup.bot.service.OutService;
import com.whatsup.bot.utils.TransformMessage;
import com.whatsup.bot.worker.messageWorker;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
/**
 *
 * @author Gonzalo_Avalos
 */
public class OutMessageProcesorServiceTest extends MockingBaseTestClass {

    @InjectMocks
    OutMessageProcesorService service;

    @Mock
    OutService outS3service;

    @Mock
    TransformMessage transformer;

    @Mock
    messageWorker worker;

    @Test
    public void processAndVerifyTest() {

        String response = null;
        try {
            response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/Events/S3EventMessage.json")));
        } catch (IOException ex) {
        }
        
        when(transformer.transform(response)).thenReturn("out/NombreKey.ext");
        when(outS3service.get("NombreKey.ext")).thenReturn(new OutMessage("NumeroTelefono", "Contenido"));
        service.process(response);
        
        verify(worker).ejecutarTarea("NumeroTelefono", "Contenido");
        
    }

}
