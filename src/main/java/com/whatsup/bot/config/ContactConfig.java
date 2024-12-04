package com.whatsup.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContactConfig {

    @Value("${server.data.path}")
    public String path;
     
    @Value("${server.data.events}")
    public String eventsPath;

   @Value("${server.data.reservas}")
    public String reservas;
   
   @Value("${server.data.out}")
    public String out;
   
    @Value("${server.data.response}")
    public String response;

}
