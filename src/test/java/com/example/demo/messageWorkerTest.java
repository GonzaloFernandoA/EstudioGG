/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.RobotInMesssageService;
import com.whatsup.bot.service.WhatsAppService;
import com.whatsup.bot.service.trackingService;
import com.whatsup.bot.worker.messageWorker;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class messageWorkerTest {

    @InjectMocks
    private messageWorker worker;

    @Mock
    trackingService tracking;
    
    @Mock
    private WhatsAppService service;

    @Mock
    private EventService eventService;
    
    @Mock
    private messageBuilder builder;
    
    @Mock
    ReservaService reserva;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ejecutarTareaENCUESTATest() {

        worker.ejecutarTarea("TELEFONO", "ENVIAR_ENCUESTA");
        verify(service).enviarMensajeTemplate("TELEFONO", null);
        verify(eventService).saveEvent("TELEFONO", "ENCUESTA_ENVIADA");

    }

    @Test
    void ejecutarTareaMensajeSimpleTest() {

        worker.ejecutarTarea("TELEFONO", "mensaje Simple");
        verify(service).sendMessage("TELEFONO", "mensaje Simple");
    }

    @Test
    void ejecutarTareaMensajeDIASTest() {
        
        List<String> diasDisponibles = new ArrayList<>();
        diasDisponibles.add("DIA1");
        diasDisponibles.add("DIA2");
        
        when(reserva.getDiasDisponibles()).thenReturn(diasDisponibles);
        when(builder.AgendaBuild(Mockito.any())).thenReturn("MENSAJEAGENDA");
        worker.ejecutarTarea("TELEFONO", "MENU_DIA");
        verify(service).sendMessage("TELEFONO", "Elija un día para que nos comuniquemos con usted (indíque la letra) : " + 
        System.lineSeparator()+ System.lineSeparator() + "MENSAJEAGENDA" );
        verify(tracking).saveFechasEnviadas("TELEFONO", diasDisponibles);
    }
    
    @Test
    void ejecutarTareaMensajeEnviarHorasTest() {

        List<String> horasDisponibles = new ArrayList<>();
        horasDisponibles.add("HORA1");
        horasDisponibles.add("HORA1");
        Tracking track = new Tracking();
        track.setFechaReservada("20241012");
        
        when(reserva.getTurnosLibres(any())).thenReturn(horasDisponibles);
        when(builder.AgendaBuildHoras(Mockito.any())).thenReturn("MENSAJEAGENDA");
        when(tracking.get("TELEFONO")).thenReturn(track);
        
        worker.ejecutarTarea("TELEFONO", "MENU_HORA_20241012");
        
        verify(service).sendMessage("TELEFONO", "Elija una hora para que nos comuniquemos con usted (indíque la letra) : " +
                        System.lineSeparator()+ System.lineSeparator() + "MENSAJEAGENDA" );
        verify(tracking).saveHorasEnviadas("TELEFONO", horasDisponibles);
    }
}