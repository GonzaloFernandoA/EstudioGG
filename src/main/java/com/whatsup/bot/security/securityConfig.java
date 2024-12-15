/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Gonzalo_Avalos
 */
@Configuration
public class securityConfig {

    @Value("${whatsapp.api.token-uri}")
    public String token_uri;

    @Value("${whatsapp.api.expiration-token-uri}")
    public String expiration_token_uri;

    @Value("${whatsapp.api.id}")
    public String id;

    @Value("${whatsapp.api.secret}")
    public String secret;

}
