package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AgendarHoraVisitaTask implements  ITask {

    @Autowired
    ReservaService reserva;

    @Autowired
    trackingService tracking;

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("DIA_VISITA");
    }

    @Override
    public String getMessage(String Name, String telefono) {
        List<String> opciones = reserva.getTurnosLibres(tracking.get(telefono).getFechaReservada());
        String message = builder.AgendaBuildHoras(opciones);
        return "Elija una hora para que nos comuniquemos con usted (indíque la letra) :" + System.lineSeparator() + "\u2B07"
                + System.lineSeparator() + message;
    }

    @Override
    public String getEventName(String telefono) {
        return "HORA_VISITA" ;
    }
}
