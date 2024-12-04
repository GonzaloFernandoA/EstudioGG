/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.message.ButtonList.Button;
import com.whatsup.bot.message.ButtonList.Root;
import com.whatsup.bot.message.ButtonList.Reply;
import com.whatsup.bot.service.agenda.BusinessDaysCalculator;
import com.whatsup.bot.service.agenda.DateUtil1;

import java.util.List;

/**
 *
 * @author Gonzalo_Avalos
 */
public class AgendaDiasBuilder {
    Root dias = new Root();
    
      
    public Root build()
    {
     //   ArrayList<Button> buttons = new ArrayList<Button>();
        
        List<String> days = BusinessDaysCalculator.getNextBusinessDays(5);
        Reply reply ;
        Button button; 
        
        for (String day : days) {
             reply = new Reply();
             reply.id = day;
             reply.title = BusinessDaysCalculator.capitalize(DateUtil1.convertDateToText(day));
             button = new Button(); 
             button.reply = reply;
             
         
            dias.interactive.action.buttons.add(button);
        }
        
             
        return dias;
    }
  
}
