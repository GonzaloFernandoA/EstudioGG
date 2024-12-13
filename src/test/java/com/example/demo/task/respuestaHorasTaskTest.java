/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.demo.task;

import com.whatsup.bot.builder.task.respuestaHorasTask;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class respuestaHorasTaskTest {

    @InjectMocks
    private respuestaHorasTask task;

    @Mock
    trackingService tracking;

    @Mock
    EventService event;

    @Mock
    EquivalenciaService equivalencia;

    @Mock
    ReservaService reserva;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TareaENCUESTAFalloValidacionTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/respuestaIncorrecta.json")));
        task.Run(response);
        verify(event, never()).saveEvent(any(), any());
        verify(equivalencia, never()).get(any());
        verify(reserva, never()).getDiaElegido(any(), any());
    }

    @Test
    void TareaENCUESTAOKValidacionTest() throws IOException {
        
        when(equivalencia.get(any())).thenReturn("TELEFONO1");
        
        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/respuestacorrecta.json")));
        task.Run(response);
        verify(event).saveOutMessage("TELEFONO1", "Respuesta inválida.");
    

    }

}
