package com.whatsup.bot.controler;

import com.whatsup.bot.security.tokenService;
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
    tokenService token;
    
    @Autowired
    WhatsAppService whatsAppService;
    private Logger log = LoggerFactory.getLogger(BotController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> exportOpportunity() {
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        whatsAppService.sendMessage(""
                + "541145587174", "Nos comunicaremos con usted a la brevedad. \uD83D\uDC4D");

        return "Message sent!";
    }

    @GetMapping("/sendMessage1")
    public String sendMessage1() {
        whatsAppService.sendMessage("5491145587174", "Nos comunicaremos con usted a la brevedad.");
        return "Message sent!";
    }

    @GetMapping("/sendTemplate")
    public String sendMessageTemplate() {
        whatsAppService.enviarMensajeTemplate("541145587174", null);
        
        return "Template sent!";
    }

    @GetMapping("/getToken")
    public String getToken() {
        token.getToken();
        return token.getToken();
    }
    
    @GetMapping("/index")
    public String home() {
        return "index";

    }
}
