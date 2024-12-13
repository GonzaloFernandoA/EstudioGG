/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.RobotInMesssageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
//@ExtendWith(MockitoExtension.class) // Permite usar anotaciones de Mockito
public class RobotInMesssageServiceTest {
    
    @BeforeEach
    public void init()
    {
        MockitoAnnotations.openMocks(this);
    }
    
    @Mock
    private EventService EventServiceMock; // Crea un mock de Repositorio
    @Mock
    private EquivalenciaService equivalenciaMock;
    
    @Test
    void contextLoads() {
    }

    @Test
    void RecibirMensajeAgendarTest() throws IOException {
        RobotInMesssageService worker = new RobotInMesssageService();
        
        worker.setEvent(EventServiceMock);
        worker.setEquivalencia(equivalenciaMock);
        when(equivalenciaMock.get("5491145587174")).thenReturn("541145587174");
        
        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/respuestaAgendarTurno.json")));
        worker.SaveInconmeMessage(response);
       
        verify(EventServiceMock).saveEvent("541145587174", "Envio agenda dia.");
        verify(EventServiceMock).saveOutMessage("541145587174", "ENVIO_MENU_DIA");
    }
    
 
}
