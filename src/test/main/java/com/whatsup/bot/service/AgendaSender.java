/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.builder.messageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class AgendaSender {
    
    @Autowired 
    WhatsAppService sender;
    
    @Autowired
    messageBuilder builder;
    
    public void Send( String telefono )
    {
        sender.sendMessage(telefono, "Elija un dia para entrevista:" + System.lineSeparator() + builder.AgendaBuild());
    }
}
