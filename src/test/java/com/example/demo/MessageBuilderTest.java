/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class messageBuilderTest {

    @InjectMocks
    messageBuilder builder;
    
    @Mock
    trackingService tracking;
    
    @Mock
    ReservaService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buildMessageTest() {
        List<String> diasDisponiblesMock = new ArrayList<>();
        diasDisponiblesMock.add("20241111");
        diasDisponiblesMock.add("20241112");

        String mensaje = builder.AgendaBuild(diasDisponiblesMock);
        Assertions.assertEquals("*A*: Lunes, 11 de noviembre" + System.lineSeparator() + "*B*: Martes, 12 de noviembre", mensaje);
    }
    
    @Test
    public void ConfirmacionMessageTest()
    {
        Tracking entity = new Tracking();
        entity.setFechaReservada("20241211");
        entity.setHoraReservada("12:00");
               
        when(tracking.get("TELEFONO1")).thenReturn(entity);

        String mensaje = builder.ConfirmacionMessage("TELEFONO1");
        Assertions.assertEquals("Confirma el turno para el día *Miércoles, 11 de diciembre* a la hora *12:00* ? "
                + System.lineSeparator() + System.lineSeparator() +"*A*: Si " + System.lineSeparator() +
                   "*B*: No" , mensaje);
    }
}
