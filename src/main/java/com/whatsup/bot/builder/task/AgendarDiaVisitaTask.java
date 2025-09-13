package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AgendarDiaVisitaTask implements ITask{

    @Autowired
    messageBuilder builder;

    @Autowired
    ReservaService reserva;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("ENCUESTA_1") ;
    }

    @Override
    public String getMessage(String Name, String telefono) {

        List<String> dias = reserva.getDiasDisponibles();
        String message = builder.AgendaBuild(dias);
        return  "Elija un día para que nos comuniquemos con usted (indíque la letra) :" + System.lineSeparator() + "\u2B07"
                + System.lineSeparator() + message;
    }

    @Override
    public String getEventName(String telefono) {
        return "DIA_VISITA";
    }
}
