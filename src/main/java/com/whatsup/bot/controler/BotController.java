package com.whatsup.bot.controler;

import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.security.tokenService;
import com.whatsup.bot.service.SqsMessagePublisher;
import com.whatsup.bot.service.WhatsAppService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BotController {
    
    @Value("${version}")
    private String buildVersion;

    @Autowired
    SqsMessagePublisher sqsMessagePublisher;

    @Autowired
    tokenService token;
    
    @Autowired
    WhatsAppService whatsAppService;
    
    private Logger log = LoggerFactory.getLogger(BotController.class);


    @GetMapping("/version")
    @ResponseBody
    public Map<String, String> getVersion() {
        return Map.of("buildVersion", buildVersion); // o la versión que quieras mostrar
    }

    @GetMapping("/health")
    public ResponseEntity<String> getHealth() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String response = "OK - " + now.format(formatter);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/sendMessage")
    public String sendMessage() {
        whatsAppService.sendMessage("541145587174", "Nos comunicaremos con usted a la brevedad.");
        return "Message sent!  --->  " + LocalDateTime.now();
    }

    @GetMapping("/sendTemplate")
    public String sendMessageTemplate() {
        whatsAppService.enviarMensajeTemplate("541145587174", "Gonzalo");
        
        return "Template sent!";
    }

    @GetMapping("/sendAlta")
    public String sendRecordatorioTemplate() {
        MessageTemplateRequest request = new MessageTemplateRequest();
        
        request.setTemplate("altaturno");
        request.setTelefono("54111545587174");
        
        request.add("Maxi Guiggi");
        request.add("Sabado 12 de Diciembre 2018");
        request.add("18:00");
        request.add("Pity Martinez");
        request.add("Bernabeu 2018, Madrid");
        request.add("Proctologia");
        
        whatsAppService.enviar(request);
        return "Template sent! --->  " + LocalDateTime.now();
    }
    



    @GetMapping("/sendConfirmacion")
    public String sendConfirmacionTemplate() {
        MessageTemplateRequest request = new MessageTemplateRequest();
        
        request.setTemplate("postconfirmacion");
        request.setTelefono("54111545587174");
        
        request.add("Maxi Guiggi");
        request.add("Pity Martinez");
        request.add("Proctologia");
        
        whatsAppService.enviar(request);
        return "Template sent!";
    }
    
    
   @GetMapping("/sendRecordatorio3")
    public String sendRecordatorio3Template() {
        MessageTemplateRequest request = new MessageTemplateRequest();
        
        request.setTemplate("recordatorio3");
        request.setTelefono("54111545587174");
        
        request.add("Maxi Guiggi");
        request.add("Sabado 12 de Diciembre 2018");
        request.add("18:00");
        request.add("Pity Martinez");
        request.add("Bernabeu 2018, Madrid");
        request.add("Proctologia");
        
        whatsAppService.enviar(request);
        return "Template sent!";
    }

    @GetMapping("/sendRecordatorio1")
    public String sendRecordatorio1Template() {
        MessageTemplateRequest request = new MessageTemplateRequest();
        
        request.setTemplate("recordatorio1");
        request.setTelefono("54111545587174");
        
        request.add("Maxi Guiggi");
        request.add("18:00");
        request.add("Pity Martinez");
        request.add("Bernabeu 2018, Madrid");
        request.add("Proctologia");
        
        whatsAppService.enviar(request);
        return "Template sent!";
    }    
    
    @GetMapping("/getToken")
    public String getToken() {
        token.getToken();
        return token.getToken();
    }
    
    @GetMapping("/index")
    public String index(Model model) {
        
   //     log.info(buildTime);
     //   model.addAttribute("buildTime", buildTime);
        return "index";
    }
}

