package com.whatsup.bot.builder.task;

import org.springframework.stereotype.Component;

@Component
public class ConfirmarVisitaTask implements ITask{
    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("HORA_VISITA");
    }

    @Override
    public String getMessage(String Name, String telefono) {
        return "";
    }

    @Override
    public String getEventName(String telefono) {
        return "CONFIRMAR_VISITA";
    }
}
