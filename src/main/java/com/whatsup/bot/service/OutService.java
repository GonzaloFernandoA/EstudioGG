/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.repository.S3RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class OutService {
    
    private Logger logger = LoggerFactory.getLogger(OutService.class);
    
    @Autowired
    S3RepositoryImpl repo;
    
    public OutMessage get(String telefonoFile)
    {
        logger.info("OutMessage: [" +telefonoFile + "]");
        OutMessage contenido = (OutMessage)repo.findByKey(telefonoFile, OutMessage.class);
        return contenido;
    }
}
