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


public class AgendarDiaVisitaTask implements ITask{

    private Logger log = LoggerFactory.getLogger(AgendarDiaVisitaTask.class);

    @Autowired
    trackingService tracking;

    @Autowired
    messageBuilder builder;

    @Autowired
    ReservaService reserva;

    @Override
    public Boolean CanRun(String lastAction) {
        log.info("AgendarDiaVisitaTask: Checking if can run for last action: {} vs {} ", lastAction, "ENCUESTA_1");
        return lastAction.equals("ENCUESTA_1") ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter ) {

        log.info("AgendarDiaVisitaTask: Generating message for {}", parameter.getPhoneNumber());
        return builder.getConcretarEntrevistaMessage();
    }

    @Override
    public String getEventName(String telefono) {
        log.info("AgendarDiaVisitaTask: Generating event name for {}", telefono);
        return "Llamar ahora para coordinar entrevista. :"  + telefono ;
    }
}


