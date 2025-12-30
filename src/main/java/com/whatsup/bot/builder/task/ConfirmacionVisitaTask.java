package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class ConfirmacionVisitaTask implements  ITask{

    @Autowired
    trackingService tracking;

    @Autowired
    ReservaService reserva;

    /**
     * This task is responsible for confirming a visit.
     * It checks if the last action was "CONFIRMAR_VISITA" and returns an empty message.
     * The event name for this task is "VISITA_CONFIRMADA".
     */
    @Autowired
    messageBuilder builder;



    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("CONFIRMAR_VISITA_1");
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {
        String dia = tracking.get(parameter.getPhoneNumber()).getFechaReservada();
        String hora = tracking.get(parameter.getPhoneNumber()).getHoraReservada();
        tracking.Confirmar(parameter.getPhoneNumber());
        reserva.reservarDiaHoraTurno(dia, hora);

        return "Turno Confirmado. Gracias 👍 .";
    }

    @Override
    public String getEventName(String telefono) {
        return "VISITA_CONFIRMADA";
    }
}
