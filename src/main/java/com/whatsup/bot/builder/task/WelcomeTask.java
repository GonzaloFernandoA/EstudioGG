package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.builder.task.turnos.Recordatorio1Task;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.message.SqsMessageTurno;
import com.whatsup.bot.service.LogMensajesService;
import com.whatsup.bot.service.SqsMessagePublisher;
import com.whatsup.bot.service.WhatsAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WelcomeTask implements ITask {
    private static final Logger log = LoggerFactory.getLogger(WelcomeTask.class);

    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

    @Autowired
    LogMensajesService logMensajesService;

    @Autowired
    WhatsAppService service;

    @Value("${whatsapp.templateName}")
    String templateEncuesta;

    @Autowired
    messageBuilder builder;

    public Boolean CanRun( String lastAction ) {
        return ( lastAction.contains("START") ) ;
    }

    public String getMessage(ExecuteParameter parameter) {
        log.info("WelcomeTask getMessage " + parameter.getName() + " telefono " + parameter.getPhoneNumber());

        MessageTemplateRequest request = new MessageTemplateRequest();
        request.setTemplate(templateEncuesta);
        request.setTelefono(parameter.getPhoneNumber());
        // request.add(parameter.getName());
        service.enviar(request);

        return null;
    }

    @Override
    public String getEventName(String telefono) {
        return "MENSAJE_WELCOME_ENVIADO";
    }
}
