package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfirmacionVisitaTask implements  ITask{

    /**
     * This task is responsible for confirming a visit.
     * It checks if the last action was "CONFIRMAR_VISITA" and returns an empty message.
     * The event name for this task is "VISITA_CONFIRMADA".
     */
    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("CONFIRMAR_VISITA");
    }

    @Override
    public String getMessage(String Name, String telefono) {
        return builder.ConfirmacionMessage(telefono);
    }

    @Override
    public String getEventName(String telefono) {
        return "VISITA_CONFIRMADA";
    }
}
