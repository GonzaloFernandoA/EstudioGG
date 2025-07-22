package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeTask implements  ITask {

    @Autowired
    messageBuilder builder;

    public Boolean CanRun(String lastAction ) {
        return lastAction == null || lastAction.isEmpty() ;
    }

    public String getMessage(String Name , String telefono) {
        return builder.construirWelcomeMensaje(Name);
    }

    @Override
    public String getEventName(String telefono) {
        return "WELCOME";
    }
}
