/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.repository.contactosRepository;
import com.whatsup.bot.repository.eventRepository;
import com.whatsup.bot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class EventService {

    @Autowired
    ContactConfig config;

    @Autowired
    contactosRepository contactRepo;
            
    @Autowired
    eventRepository eventRepo   ;   
    
    public void getAll()
    {
        
    }
    
    private void saveFile(String id, Object evento, String path) {
        JsonUtils.writeJsonToFile(path + id, evento);
    }

    public void saveEvent(String id, String evento) {
        this.saveFile(id, evento, config.eventsPath);
    }

    public void saveOutMessage(String id, String evento) {
        this.saveFile(id, evento, config.out);
    }

    public void saveResponse(String id, ResponseRoot evento) {
        
            this.saveFile(id, evento, config.response);
       
    }
}
