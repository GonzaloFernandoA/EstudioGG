/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo_Avalos
 */
@RestController
@RequestMapping("/api/templates")
public class TemplatesController {
    
    private Logger log = LoggerFactory.getLogger(TemplatesController.class);
    
    @Autowired
    EventService eventService;
    
    @PostMapping
    public void Post(@RequestBody MessageTemplateRequest request )
    {
       log.info(request.toString());
       eventService.saveOutMessage(request.getTelefono(),request.toJson());
    }
}

