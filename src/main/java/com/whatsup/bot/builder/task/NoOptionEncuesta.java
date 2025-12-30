package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class NoOptionEncuesta implements ITask {

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        return ( lastAction.contains("ENCUESTA_")
                && !lastAction.equals("ENCUESTA_1")
                && !lastAction.equals("ENCUESTA_2")
                && !lastAction.equals("ENCUESTA_3") )
                ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {

        String mensaje = "Por favor seleccione una de las opciones disponibles: \n" +
                builder.construirMensajeOpciones(parameter.getName()) ;

        return mensaje;
    }

    @Override
    public String getEventName(String telefono) {
        return "ENCUESTA";
    }
}
