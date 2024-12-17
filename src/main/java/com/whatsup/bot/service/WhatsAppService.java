/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.WhatsupSecurityConfig;
import com.whatsup.bot.message.ButtonList.Root;
import com.whatsup.bot.message.Component;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.security.tokenService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class WhatsAppService {

    @Autowired
    tokenService tokens; 
    
    @Autowired
    RobotInMesssageService Service;

    @Autowired
    WhatsupSecurityConfig config;

    private final WebClient webClient;

    @Autowired
    public WhatsAppService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void enviarMensajeTemplate(String numeroDestino, Map<String, String> parametros) {
        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", numeroDestino);
        body.put("type", "template");

        Map<String, Object> template = new HashMap<>();
        template.put("name", config.getTemplateName());
        template.put("language", Map.of("code", "es_AR")); // Cambia el idioma si es necesario

        List<Component> componentes = new ArrayList<>();
        Component componente = new Component();
        componentes.add(componente);
        template.put("components", componentes);
        body.put("template", template);

        this.sendObject(numeroDestino, body);
    }

    private WebClient getWebClient()
    {
        return this.webClient.mutate()
                .defaultHeader("Authorization", "Bearer " + tokens.getCurrentToken())
                .build();
    }
    
    public void sendObject(String numeroDestino, Map<String, Object> mensaje) {

                getWebClient().post()
                .header("Content-Type", "application/json")
                .bodyValue(mensaje)
                .retrieve()
                .bodyToMono(ResponseRoot.class)
                .doOnSuccess(response -> Service.SaveInconmeMessage(response) )
                .doOnError(error -> System.err.println("Error al enviar el mensaje: " + error.getMessage()))
                .subscribe();
  
    }

    public void sendObject(Root mensaje) {
        getWebClient().post()
                .header("Content-Type", "application/json")
                .bodyValue(mensaje)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> System.out.println("Mensaje enviado correctamente: " + response))
                .doOnError(error -> System.err.println("Error al enviar el mensaje: " + error.getMessage()))
                .subscribe();
    }

    public Map<String, Object> sendMessage(String recipientPhoneNumber, String messageText) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("messaging_product", "whatsapp");
        payload.put("to", recipientPhoneNumber);
        payload.put("type", "text");

        Map<String, String> text = new HashMap<>();
        text.put("body", messageText);
        payload.put("text", text);

        this.sendObject(recipientPhoneNumber, payload);

        return payload;
    }

    public ResponseEntity<Void> getResponse(@RequestBody String payload,
            @RequestHeader("X-Hub-Signature") String signature) {
        try {
            String appSecret = "your_app_secret";
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(appSecret.getBytes(), "HmacSHA256");
            mac.init(secretKeySpec);
            //byte[] digest = mac.doFinal(payload.getBytes());

            String calculatedSignature = "sha256="; // + Hex.encodeHexString(digest);

            if (!calculatedSignature.equals(signature)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            // Procesa el payload aquí
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * @return the webClient
     */
}
