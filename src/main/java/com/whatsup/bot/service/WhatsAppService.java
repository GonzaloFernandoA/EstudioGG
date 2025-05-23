/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.ComponentTemplateBuilder;
import com.whatsup.bot.config.WhatsupSecurityConfig;
import com.whatsup.bot.message.ButtonList.Root;
import com.whatsup.bot.message.ComponentBody;
import com.whatsup.bot.message.ComponentHeader;
import com.whatsup.bot.message.IComponent;
import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.security.tokenService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class WhatsAppService {

    private Logger log = LoggerFactory.getLogger(WhatsAppService.class);
    
    
    @Autowired
    tokenService tokens; 
    
    @Autowired
    RobotInMesssageService Service;

    @Autowired
    WhatsupSecurityConfig config;

    @Autowired
    private ObjectMapper objectMapper;

    
    private final WebClient webClient;

    @Autowired
    public WhatsAppService(WebClient webClient) {
        this.webClient = webClient;
    }

    public void enviar(MessageTemplateRequest request ){
        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", request.getTelefono());
        body.put("type", "template");

        Map<String, Object> template = new HashMap<>();
        template.put("name", request.getTemplate());
        template.put("language", Map.of("code", "es_AR"));

        List<IComponent> componentes = new ArrayList<>();
        IComponent componente = new ComponentTemplateBuilder(request).getComponent();
        componentes.add(componente);
        
     //   IComponent componenteHeader = new ComponentHeader();
     //   componentes.add(componenteHeader);
        
        template.put("components", componentes);
        body.put("template", template);

        this.sendObject(request.getTelefono(), body);
    }

    
    
    public void enviarMensajeTemplate(String numeroDestino, String nombre ) {
        Map<String, Object> body = new HashMap<>();
        body.put("messaging_product", "whatsapp");
        body.put("to", numeroDestino);
        body.put("type", "template");

        Map<String, Object> template = new HashMap<>();
        template.put("name", config.getTemplateName());
        template.put("language", Map.of("code", "es_AR")); // Cambia el idioma si es necesario

        List<IComponent> componentes = new ArrayList<>();
        IComponent componente = new ComponentBody(nombre);     
        componentes.add(componente);
        
        IComponent componenteHeader = new ComponentHeader();
        componentes.add(componenteHeader);
        
        template.put("components", componentes);
        body.put("template", template);

        this.sendObject(numeroDestino, body);
    }

    private WebClient getWebClient()
    {
        return this.webClient.mutate()
                .defaultHeader("Authorization", "Bearer " + tokens.getToken())
                .build();
    }
    
    public void sendObject(String numeroDestino, Map<String, Object> mensaje) {

           
        try {
            String payloadJson = objectMapper.writeValueAsString(mensaje);
            log.info("➡️ Enviando POST a WebService con body: {}", payloadJson);
        } catch (JsonProcessingException e) {
            log.warn("Error al serializar payload", e);
        }

        getWebClient().post()
        .header("Content-Type", "application/json")
        .bodyValue(mensaje)
        .retrieve()
        .onStatus(status -> status.is4xxClientError(), response ->  
        response.bodyToMono(String.class)
                  .flatMap(errorBody -> { log.error( "Error http" , response.statusCode(), errorBody) ;
                  return Mono.error(new RuntimeException("Error whasapp" + errorBody));
                  })
        )
        .bodyToMono(ResponseRoot.class)
        .doOnSuccess(response -> Service.SaveInconmeMessage(response) )
        .doOnError(error -> log.error("Error al enviar el mensaje: " + error.getMessage()))
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
