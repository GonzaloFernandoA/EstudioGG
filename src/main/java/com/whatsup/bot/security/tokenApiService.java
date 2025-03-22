/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class tokenApiService {

    private final Logger logger = LoggerFactory.getLogger(tokenApiService.class);
    
    @Autowired
    securityConfig config;

    public String Renew(String currentToken) {
        String url = config.token_uri;

        String newToken = "";
        RestTemplate restTemplate = new RestTemplate();

        // Construir parámetros para la solicitud
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "fb_exchange_token");
        params.put("client_id", config.id);
        params.put("client_secret", config.secret);
        params.put("fb_exchange_token", currentToken);

        try {
            Map<String, Object> response = restTemplate.postForObject(url, params, Map.class);
            // Actualizar el token actual
            newToken = (String) response.get("access_token");
            System.out.println("Nuevo token obtenido: " + newToken);
        } catch (Exception e) {
            System.err.println("Error al renovar el token: " + e.getMessage());
        }
        return newToken;
    }

}
