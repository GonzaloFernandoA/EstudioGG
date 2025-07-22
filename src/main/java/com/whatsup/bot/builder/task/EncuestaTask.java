package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncuestaTask implements  ITask{

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lasAction) {
        return lasAction.equals("WELCOME");
    }

    @Override
    public String getMessage(String Name, String telefono) {
        return builder.construirMensajeOpciones(Name);
    }

    @Override
    public String getEventName(String telefono) {
        return "ENCUESTA";
    }
}
