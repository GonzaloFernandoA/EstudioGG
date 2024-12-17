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
import com.whatsup.bot.repository.tokenRepository;
import com.whatsup.bot.utils.DateTimeUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class tokenService {

    private final Logger logger = LoggerFactory.getLogger(tokenService.class);

    @Autowired
    tokenRepository repo; 
    
    @Autowired
    securityConfig config;

    @Autowired
    tokenExpirationService expService; 
    
  //  @Scheduled(cron = "0 0 0 */85 * ?") // Ejecutar cada 85 días
    public String renewToken() {
        String url = config.token_uri;
        
        String newToken = "";
        RestTemplate restTemplate = new RestTemplate();

        // Construir parámetros para la solicitud
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "fb_exchange_token");
        params.put("client_id", config.id);
        params.put("client_secret", config.secret);
        params.put("fb_exchange_token", getCurrentToken());

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

    public Date getExpirationToken()
    {
        return expService.getTokenExpirationDate(getCurrentToken());
    }
            
    
    public String getToken() {
        
        String token = getCurrentToken();
        Date expirationDate = expService.getTokenExpirationDate(token);
        logger.info("Expiration date: " + expirationDate);
        if ( DateTimeUtils.isDifferenceLessThanNDays(expirationDate, 5) )
            token = this.renewToken();
        
        return token;
    }
    
    public String getCurrentToken()
    {
        return config.token;
    }
    
    public void saveToken(String secret)
    {
        Token token = new Token();
        token.setSecret(secret);
        token.setValidFrom(new Date());
    }
    
}
