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
public class HorarioSender {
    
    public void Send( String telefono, WhatsAppService sender )
    {
        sender.sendMessage(telefono, "Elija una hora para entrevista:" + System.lineSeparator() );
    }
}
