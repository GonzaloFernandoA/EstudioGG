/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.utils.TransformMessage;
import com.whatsup.bot.worker.messageWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class OutMessageProcesorService {

    private final Logger logger = LoggerFactory.getLogger(OutMessageProcesorService.class);
    
    @Autowired
    CarpetasConfig config; 
    
    @Autowired
    OutService service; 
    
    @Autowired
    TransformMessage transformer; 
    
    @Autowired
    messageWorker worker; 
    
    
    public void process(String body) {
        
        String S3ObjectName = transformer.transform(body);
        S3ObjectName = S3ObjectName.replace(config.getOut(), "");
        OutMessage outMessage = service.get(S3ObjectName);
        worker.ejecutarTarea(outMessage.getTelefono(), outMessage.getContenido());
    }
    
}
