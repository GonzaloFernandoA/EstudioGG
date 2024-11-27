package com.whatsup.bot.controler;

import com.whatsup.bot.service.WhatsAppService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {

    @Autowired
    WhatsAppService whatsAppService;
    private Logger log = LoggerFactory.getLogger(BotController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> exportOpportunity() {

        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        whatsAppService.sendMessage("54111545587174", "Nos comunicaremos con usted a la brevedad.");
        return "Message sent!";
    }
    
    @GetMapping("/sendTemplate")
    public String sendMessageTemplate() {
        whatsAppService.enviarMensajeTemplate("54111545587174",null);
        return "Message sent!";
    }

    @GetMapping("/index")
    public String home() {
        return "index";

    }
}
