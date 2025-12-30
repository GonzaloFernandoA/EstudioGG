/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;

/**
 *
 * @author Gonzalo_Avalos
 */

public interface ITask {

    Boolean CanRun(String lastAction);

    String getMessage(ExecuteParameter parameter);

    String getEventName(String telefono);
    
}
