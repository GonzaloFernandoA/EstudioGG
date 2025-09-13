/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class AsesoramientoVirtualTask implements  ITask{

    @Autowired
    messageBuilder builder;

    @Override
    public Boolean CanRun(String lastAction) {
        return lastAction.equals("ENCUESTA_2") ;
    }

    @Override
    public String getMessage(String Name, String telefono) {
        return builder.getAsesoramientoVirtualMessage();
    }

    @Override
    public String getEventName(String telefono) {
        return "Llamar ahora. :"  + telefono ;
    }
}
