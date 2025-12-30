package com.whatsup.bot.builder.task.turnos;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.task.ITask;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.service.LogMensajesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.whatsup.bot.service.SqsMessagePublisher;
import com.whatsup.bot.message.SqsMessageTurno;

@Component
public class AltaTask implements ITask {

    private static final Logger log = LoggerFactory.getLogger(AltaTask.class);
    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

    @Autowired
    LogMensajesService logMensajesService;


    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.contains("altaturno_") ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {

        log.info("AltaTask getMessage " + parameter.getName() + " telefono " + parameter.getPhoneNumber());
        LogMensajes message = logMensajesService.get(parameter.getWaId());
        log.info("AltaTask getMessage " + "result " + parameter.getMessage() + " message:"+message.getId());
        SqsMessageTurno messageTurno = new SqsMessageTurno(message.getId(), "altaturno", parameter.getMessage());

        sqsMessagePublisher.sendMessage(messageTurno);
        return null;
    }

    @Override
    public String getEventName(String telefono) {
        return "RECORDATORIO_3";
    }

}
