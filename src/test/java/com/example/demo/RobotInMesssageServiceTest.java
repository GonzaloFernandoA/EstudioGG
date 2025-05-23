/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.builder.task.respuestaHorasTask;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.RobotInMesssageService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class RobotInMesssageServiceTest {

    

    @InjectMocks
    RobotInMesssageService service;

    @Mock
    respuestaHorasTask respuestaHoras ;
    
    @Mock
    ReservaService reserva;
    
    @Mock
    EquivalenciaService equivalencia;

    @Mock
    EventService event;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

 /*   @Test
    void MensajeRespuestaAEleccionDeDiaTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/respuestaEligioDIa.json")));
        when(equivalencia.get("5491145587174")).thenReturn("TELEFONO1");
        when(reserva.getDiaElegido("TELEFONO1", "C" )).thenReturn("20241201");

        service.SaveInconmeMessage(response);

   //     verify(event).saveOutMessage("TELEFONO1", "MENU_HORA_20241201");
     //   verify(event).saveEvent("TELEFONO1", "MENU HORA");
        verify(respuestaHoras).Run(response);
        
    }
*/
}
