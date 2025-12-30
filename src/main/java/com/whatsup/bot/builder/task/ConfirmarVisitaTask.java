package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.trackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;


public class ConfirmarVisitaTask implements ITask{

    private Logger log = LoggerFactory.getLogger(ConfirmarVisitaTask.class);

    @Autowired
    messageBuilder builder;

    @Autowired
    trackingService tracking;

    private String action = "";

    String eventName = "CONFIRMAR_VISITA";

    @Override
    public Boolean CanRun(String lastAction) {

        Boolean canRun = lastAction != null && lastAction.contains("HORA_VISITA");
        if (canRun)  {action = lastAction.replace("HORA_VISITA_", "").trim();}
        return canRun;
    }

    @Override
    public String getMessage(ExecuteParameter parameter )  {

        log.info("ConfirmarVisitaTask: Saving reservation for phone: {} with action: {}", parameter.getPhoneNumber(), parameter.getLastAction());
        String horaSeleccionada = tracking.getHoraSegunOpcion(parameter.getPhoneNumber(), parameter.getLastAction());
        if (horaSeleccionada == null || horaSeleccionada.isEmpty()) {
            log.error("Opcion elegida no válida. "
                    + "Telefono: {}, Name: {}, Action {}", parameter.getPhoneNumber(), parameter.getName(), parameter.getLastAction());
            this.eventName = "HORA_VISITA";
            return "La hora seleccionada no es válida. Por favor, inténtelo de nuevo.";
        }
        this.eventName = "CONFIRMAR_VISITA";

        tracking.saveHoraReservada(parameter.getPhoneNumber(), horaSeleccionada);
        return builder.ConfirmacionMessage(parameter.getPhoneNumber());
    }

    @Override
    public String getEventName(String telefono) {
        return eventName;
    }
}
