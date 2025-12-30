package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.trackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class NoConfirmacionVisitaTask implements ITask{
    /**
     * This task is responsible for handling the case when a visit is not confirmed.
     * It checks if the last action was "NO_CONFIRMAR_VISITA" and returns an empty message.
     * The event name for this task is "VISITA_NO_CONFIRMADA".
     */
    @Autowired
    trackingService tracking;

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("CONFIRMAR_VISITA_2");
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {
            tracking.Delete(parameter.getPhoneNumber());

            return "No hay problema, podemos comenzar nuevamente" + System.lineSeparator() +
        builder.construirMensajeOpciones(parameter.getName());
        }

    @Override
    public String getEventName(String telefono) {
        return "ENCUESTA";
    }
}
