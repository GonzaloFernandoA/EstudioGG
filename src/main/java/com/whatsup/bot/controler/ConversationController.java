package com.whatsup.bot.controler;

import com.whatsup.bot.service.ConversationService;
import com.whatsup.bot.entity.Conversation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    // src/main/java/com/whatsup/bot/controler/ConversationController.java
    @PostMapping("/Conversation")
    public ResponseEntity<Void> saveConversation(@RequestBody Conversation conversation) {
        conversationService.save(conversation.getPhoneNumber(), conversation.getLastMessage());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
