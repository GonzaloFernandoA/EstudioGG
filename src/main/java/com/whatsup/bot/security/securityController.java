/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

import com.whatsup.bot.entity.Token;
import com.whatsup.bot.repository.S3RepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo_Avalos
 */
@RestController
public class securityController {
    
    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    tokenService service; 
    
    @PostMapping("/token")
    public String setTemporalToken(@RequestParam("token1") String temporalToken) {
        service.renewToken(temporalToken);
        return "OK";
    }
}
