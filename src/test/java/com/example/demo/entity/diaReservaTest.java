/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.entity;

import com.whatsup.bot.entity.DiaReserva;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class diaReservaTest {

    @Test
    void contextLoads() {
    }

    @Test
    void getTieneTodosLosHorarioDisponiblesTest() {

        DiaReserva dia = new DiaReserva();

        Assertions.assertTrue(dia.TieneHorariosDisponibles());
    }

    @Test
    void getTieneAlgunosHorariosDisponiblesTest() {

        DiaReserva dia = new DiaReserva();
        dia.getHorariosOcupados().add("10:00");
        Assertions.assertTrue(dia.TieneHorariosDisponibles());
    }

    @Test
    void getNoTieneHorariosDisponiblesTest() {

        DiaReserva dia = new DiaReserva();
        dia.getHorariosOcupados().add("10:00");
        dia.getHorariosOcupados().add("11:00");
        dia.getHorariosOcupados().add("12:00");
        dia.getHorariosOcupados().add("13:00");
        dia.getHorariosOcupados().add("14:00");
        dia.getHorariosOcupados().add("15:00");
        dia.getHorariosOcupados().add("16:00");
        dia.getHorariosOcupados().add("17:00");
        Assertions.assertFalse(dia.TieneHorariosDisponibles());
    }
}
