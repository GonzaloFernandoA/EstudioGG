/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

/**
 *
 * @author Gonzalo_Avalos
 */
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class tokenService {
    @Autowired
    securityConfig config;
    
    private String currentToken = "EAAP998NXCyIBOyZB2pklb0131c6ZBZBmdCXDbo2ZBZBZBUHixI3pQKlZBeZAZBR4f74GP59Q2pTHcUoZAjHsxNdwy8PBHJILG4qZBsZCzQBD9dKByXKgRS92SkZAKJ1ZBbOQ17qwoWa02PHoTBZAVSloDMLgsLZBROByOHeTACuZBVjpBWDFwufTRJxn4ZAZCBJAtZCDjgTkBQYUR6iazS294teFEDZCjvJXnDAmS";

    @Scheduled(cron = "0 0 0 */85 * ?") // Ejecutar cada 85 días
    public void renewToken() {
        String url = config.token_uri;
        
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
            currentToken = (String) response.get("access_token");
            System.out.println("Nuevo token obtenido: " + currentToken);
        } catch (Exception e) {
            System.err.println("Error al renovar el token: " + e.getMessage());
        }
    }

    public String getToken() {
        this.renewToken();
        return currentToken;
    }
}

