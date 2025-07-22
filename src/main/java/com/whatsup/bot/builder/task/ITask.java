/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.whatsup.bot.builder.task;

/**
 *
 * @author Gonzalo_Avalos
 */

public interface ITask {
    Boolean CanRun(String lastAction);

    String getMessage(String Name, String telefono);

    String getEventName(String telefono);
    
}
