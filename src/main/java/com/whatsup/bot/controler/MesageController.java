package com.whatsup.bot.controler;

import com.whatsup.bot.message.WebHookResponse.WebHookMessageWIthSender;
import com.whatsup.bot.service.RobotInMesssageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MesageController {

    private Logger log = LoggerFactory.getLogger(MesageController.class);

    @Autowired
    RobotInMesssageService robot;

    @PostMapping("/api/message")
    public ResponseEntity<String> receiveMessage(@RequestBody WebHookMessageWIthSender incomingMessage) {
        // Handle the incoming message here
        String sender = incomingMessage.getSender();
        String message = incomingMessage.getText();

        robot.SaveInconmeMessage(incomingMessage.toString());

        log.info("Received message from {}: {}", sender, message);
        return ResponseEntity.ok("Message received from " + sender + ": " + message);
    }
}