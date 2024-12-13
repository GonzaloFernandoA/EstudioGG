/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 *
 * @author Gonzalo_Avalos
 */
@Configuration
public class WebClientConfig {

    @Value("${whatsapp.api.base-url}")
    private String baseUrl;

    @Value("${whatsapp.api.token}")
    private String token;

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(baseUrl)
                .filter(logRequest()) // Registrar la solicitud
                .defaultHeader("Authorization", "Bearer " + token)
                .defaultHeader("Content-Type", "application/json")
                .build();
        
     //     .filter(logResponse()) // Registrar la respuesta
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value
                    -> System.out.println(name + ": " + value)
            ));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            System.out.println("Response Status: " + clientResponse.statusCode());
            return clientResponse.bodyToMono(String.class)
                    .doOnNext(body -> System.out.println("Response Body: " + body))
                    .then(Mono.just(clientResponse));
        });
    }
}
