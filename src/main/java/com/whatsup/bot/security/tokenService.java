/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

/**
 *
 * @author Gonzalo_Avalos
 */
import com.whatsup.bot.entity.Token;
import com.whatsup.bot.repository.S3RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class tokenService {

    private final Logger logger = LoggerFactory.getLogger(tokenService.class);

    @Autowired
    securityConfig config; 
    
    public String getToken() {

        return config.token;

    }

}
