package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public class AgendarHoraVisitaTask implements ITask {

    private Logger log = LoggerFactory.getLogger(AgendarHoraVisitaTask.class);

    private String action ="";

    @Autowired
    ReservaService reserva;

    @Autowired
    trackingService tracking;

    @Autowired
    messageBuilder builder;

    String eventName = "HORA_VISITA";

    @Override
    public Boolean CanRun(String lastAction) {
        log.info("AgendarHoraVisitaTask: Checking if can run for last action: {} vs {} ", lastAction, "DIAS_ENVIADOS");
        Boolean canRun = lastAction != null && lastAction.contains("DIAS_ENVIADOS");
        if (canRun)  {action = lastAction.replace("DIAS_ENVIADOS_", "").trim();}

        return canRun;
    }

    @Override
    public String getMessage(ExecuteParameter parameter ) {

        String fechaSeleccionada = tracking.getFechaSegunOpcion(parameter.getPhoneNumber(), parameter.getLastAction());
        if (fechaSeleccionada == null || fechaSeleccionada.isEmpty()) {
            log.error("Opcion elegida no válida. "
                    + "Telefono: {}, Name: {}, Action {}", parameter.getPhoneNumber(), parameter.getName(), action);
            this.eventName = "DIAS_ENVIADOS" ;
            return "La fecha seleccionada no es válida. Por favor, inténtelo de nuevamente.";
        }

        tracking.saveFechasReservada(parameter.getPhoneNumber(), fechaSeleccionada);
        List<String> opciones = reserva.getTurnosLibres(tracking.get(parameter.getPhoneNumber()).getFechaReservada());
        String message = builder.AgendaBuildHoras(opciones);
        tracking.saveHorasEnviadas(parameter.getPhoneNumber(), opciones);

        this.eventName = "HORA_VISITA";

        return "Elija una hora para que nos comuniquemos con usted (indíque un número) :" + System.lineSeparator() + "\u2B07"
                + System.lineSeparator() + message;
    }

    @Override
    public String getEventName(String telefono) {
        return eventName;
    }
}
