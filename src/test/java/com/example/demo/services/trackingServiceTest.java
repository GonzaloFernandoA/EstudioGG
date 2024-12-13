/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.repository.trackingRepository;
import com.whatsup.bot.service.trackingService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class trackingServiceTest {

    @InjectMocks
    trackingService service;

    @Mock
    private trackingRepository repo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getEmptyDayTest() {

        when(repo.getOrDefault("TELEFONO1")).thenReturn(new Tracking());

        List<String> fechasEnviadas = new ArrayList<>();
        fechasEnviadas.add("20241012");
        fechasEnviadas.add("20241013");
        fechasEnviadas.add("20241014");

        service.saveFechasEnviadas("TELEFONO1", fechasEnviadas);
        verify(repo).save(eq("TELEFONO1"), any());
    }

    @Test
    void getFechaSegunOpcionTest() {

        Tracking tracking = new Tracking();

        List<String> fechasEnviadas = new ArrayList<>();
        fechasEnviadas.add("20241012");
        fechasEnviadas.add("20241013");
        fechasEnviadas.add("20241014");

        tracking.setFechasEnviadas(fechasEnviadas);
        String fechaElegida = service.getFechaSegunOpcion(tracking, 'C');
        Assertions.assertEquals("20241014", fechaElegida);
    }

    @Test
    void getFechaSegunOpcionPrimeraOpcionTest() {

        Tracking tracking = new Tracking();
        List<String> fechasEnviadas = new ArrayList<>();
        fechasEnviadas.add("20241012");
        fechasEnviadas.add("20241013");
        fechasEnviadas.add("20241014");

        tracking.setFechasEnviadas(fechasEnviadas);
        String fechaElegida = service.getFechaSegunOpcion(tracking, 'a');
        Assertions.assertEquals("20241012", fechaElegida);
    }

    @Test
    void getHoraSegunOpcionPrimeraOpcionTest() {

        Tracking tracking = new Tracking();

        List<String> horasEnviadas = new ArrayList<>();
        horasEnviadas.add("hora1");
        horasEnviadas.add("hora2");
        horasEnviadas.add("hora3");

        tracking.setHorasEnviadas(horasEnviadas);

        String horaElegida = service.getHoraSegunOpcion(tracking, 'B');

        Assertions.assertEquals("hora2", horaElegida);
    }

    @Test
    void getNoTieneFechaReservadaTest() {

        Tracking tracking = new Tracking();
        when(repo.getOrDefault("TELEFONO1")).thenReturn(tracking);
        Assertions.assertTrue(service.isReservaDiasIsBlank("TELEFONO1"));
    }

    @Test
    void getTieneFechaReservadaTest() {

        Tracking tracking = new Tracking();
        tracking.setFechaReservada("HOY");
        when(repo.getOrDefault("TELEFONO1")).thenReturn(tracking);
        Assertions.assertFalse(service.isReservaDiasIsBlank("TELEFONO1"));
    }

    @Test
    void getNoTurnoHoraReservadaTest() {

        Tracking tracking = new Tracking();
        when(repo.getOrDefault("TELEFONO1")).thenReturn(tracking);
        Assertions.assertTrue(service.isReservaHorasIsBlank("TELEFONO1"));
    }

    @Test
    void getTieneTurnoHoraReservadaTest() {

        Tracking tracking = new Tracking();
        tracking.setHoraReservada("AHORA");
        when(repo.getOrDefault("TELEFONO1")).thenReturn(tracking);
        Assertions.assertFalse(service.isReservaHorasIsBlank("TELEFONO1"));
    }

    @Test
    void getHoraSegunOpcionConTelefonoTest() {
        Tracking tracking = new Tracking();

        List<String> horasEnviadas = new ArrayList<>();

        horasEnviadas.add("hora1");
        horasEnviadas.add("hora2");
        horasEnviadas.add("hora3");
        tracking.setHorasEnviadas(horasEnviadas);
       
        when(repo.getOrDefault("TELEFONO1")).thenReturn(tracking);

        Assertions.assertEquals( "hora2", service.getHoraSegunOpcion("TELEFONO1", "B") ); 
        
    }
}
