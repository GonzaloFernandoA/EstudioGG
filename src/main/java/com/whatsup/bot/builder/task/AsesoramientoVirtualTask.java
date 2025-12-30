/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.BotApplication;
import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class AsesoramientoVirtualTask implements  ITask{

    private Logger log = LoggerFactory.getLogger(AsesoramientoVirtualTask.class);

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        log.info("AsesoramientoVirtualTask: Checking if can run for last action: {} vs {} ", lastAction, "ENCUESTA_1");
        return lastAction.equals("MENSAJE_WELCOME_ENVIADO_Necesito asesoramiento.") ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {
        log.info("AsesoramientoVirtualTask: Generating message for {}", parameter.getPhoneNumber());
        return builder.getAsesoramientoVirtualMessage();
    }

    @Override
    public String getEventName(String telefono) {
        log.info("AsesoramientoVirtualTask: Generating event name for {}", telefono);
        return "Llamar ahora. :"  + telefono ;
    }
}
