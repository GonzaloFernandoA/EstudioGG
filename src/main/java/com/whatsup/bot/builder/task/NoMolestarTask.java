package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import org.springframework.stereotype.Component;


public class NoMolestarTask implements ITask {

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("ENCUESTA_3");
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {
        return "Gracias por contactarnos evitaremos molestarlo con mensajes innecesarios.";
    }

    @Override
    public String getEventName(String telefono) {
        return "NO_MOLESTAR";
    }

}
