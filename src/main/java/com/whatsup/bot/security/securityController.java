/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

import com.whatsup.bot.repository.S3RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo_Avalos
 */
@RestController
public class securityController {
    
    private Logger log = LoggerFactory.getLogger(securityController.class);
    
    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    tokenService service; 
    
    @PostMapping("/token")
    public String setTemporalToken(@RequestBody String temporalToken) {
        
        return "OK";
    }
}
