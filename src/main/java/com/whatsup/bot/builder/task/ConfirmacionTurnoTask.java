/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class ConfirmacionTurnoTask {

    @Autowired
    trackingService tracking;

    @Autowired
    ReservaService reserva;

    public Boolean IsValid(String respuesta) {
        return ("1".equals(respuesta) || "2".equals(respuesta));
    }

    public void Process(String telefono) {
        String dia = tracking.get(telefono).getFechaReservada();
        String hora = tracking.get(telefono).getHoraReservada();

        reserva.reservarDiaHoraTurno(dia, hora);
    }

    public void Run(String respuesta, String telefono) {
        if (IsValid(respuesta)) {
            Process(telefono);
        }
    }
}
