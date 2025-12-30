package com.whatsup.bot.builder.task.turnos;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.task.ITask;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.message.SqsMessageTurno;
import com.whatsup.bot.service.LogMensajesService;
import com.whatsup.bot.service.SqsMessagePublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostConfirmacionVisitaTask implements ITask {

    private static final Logger log = LoggerFactory.getLogger(PostConfirmacionVisitaTask.class);

    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

    @Autowired
    LogMensajesService logMensajesService;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.contains("CONFIRMACION_");
    }

    @Override
    public String getMessage(ExecuteParameter parameter)
    {
        log.info("ConfirmacionVisitaTask getMessage " + parameter.getName() + " telefono " + parameter.getPhoneNumber());
        LogMensajes message = logMensajesService.get(parameter.getWaId());
        log.info("ConfirmacionVisitaTask getMessage " + "result " + parameter.getMessage() + " message:"+message.getId());
        SqsMessageTurno messageTurno = new SqsMessageTurno(message.getId(), "postconfirmacion", parameter.getMessage());

        sqsMessagePublisher.sendMessage(messageTurno);

        return null;
    }

    @Override
    public String getEventName(String telefono) {
        return "FINALIZACION";
    }
}
