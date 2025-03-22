/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.repository.S3RepositoryImpl;
import com.whatsup.bot.service.ReservaService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class reservaServicetest {

    @InjectMocks
    ReservaService service;
    
    @Mock
    private S3RepositoryImpl repo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getEmptyDayTest() {

        service.reservarDiaHoraTurno("20200122", "10:00");
        verify(repo).save("turnos/20200122", any());
    }

    @Test
    void getTurnosLibresTest() {
        DiaReserva reserva = new DiaReserva();
        when(repo.findByKey("turnos/20200122", any())).thenReturn(reserva);

        List<String> turnos = service.getTurnosLibres("20200122");

        Assertions.assertEquals(8, turnos.size());
        Assertions.assertEquals("10:00", turnos.get(0));
        Assertions.assertEquals("17:00", turnos.get(7));
        
    }

    @Test
    void getAlgunosLibresTest() {
        DiaReserva reserva = new DiaReserva();
        reserva.getHorariosOcupados().add("11:00");

        when(repo.findByKey("turnos/20200122", any())).thenReturn(reserva);

        List<String> turnos = service.getTurnosLibres("20200122");

        Assertions.assertEquals(7, turnos.size());

    }

    @Test
    void getACasiodosOcupadosTest() {
        DiaReserva reserva = new DiaReserva();
        reserva.getHorariosOcupados().add("11:00");
        reserva.getHorariosOcupados().add("12:00");
        reserva.getHorariosOcupados().add("13:00");
        reserva.getHorariosOcupados().add("16:00");
        reserva.getHorariosOcupados().add("17:00");

        when(repo.findByKey("turnos/20200122",any())).thenReturn(reserva);

        List<String> turnos = service.getTurnosLibres("20200122");

        Assertions.assertEquals(3, turnos.size());

        //   verify(repo).save(8, turnos.size());
    }

    @Test
    void getTodosOcupadosTest() {
        DiaReserva reserva = new DiaReserva();
        reserva.getHorariosOcupados().add("11:00");
        reserva.getHorariosOcupados().add("12:00");
        reserva.getHorariosOcupados().add("13:00");
        reserva.getHorariosOcupados().add("16:00");
        reserva.getHorariosOcupados().add("17:00");
        reserva.getHorariosOcupados().add("10:00");
        reserva.getHorariosOcupados().add("14:00");
        reserva.getHorariosOcupados().add("15:00");

        when(repo.findByKey("turnos/20200122",any())).thenReturn(reserva);

        List<String> turnos = service.getTurnosLibres("20200122");

        Assertions.assertEquals(0, turnos.size());

    }
    
    @Test
    void getObtenerDiasLibresTest() {
        DiaReserva reserva = new DiaReserva();
        reserva.getHorariosOcupados().add("11:00");
        reserva.getHorariosOcupados().add("12:00");
        reserva.getHorariosOcupados().add("13:00");
        reserva.getHorariosOcupados().add("16:00");
        reserva.getHorariosOcupados().add("17:00");
        reserva.getHorariosOcupados().add("10:00");
        reserva.getHorariosOcupados().add("14:00");
        reserva.getHorariosOcupados().add("15:00");

        when(repo.findByKey("turnos/20200122",any())).thenReturn(reserva);

        List<String> turnos = service.getTurnosLibres("20200122");
        Assertions.assertEquals(0, turnos.size());

    }
}
