/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.builder.task.ITask;
import com.whatsup.bot.builder.task.respuestaHorasTask;

/**
 *
 * @author Gonzalo_Avalos
 */
public class TaskBuilder {
    
    public ITask Create(String IncomeMessage)
            
    {
        return new respuestaHorasTask();
    }
}
