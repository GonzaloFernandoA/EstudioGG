package com.whatsup.bot.service;


import com.whatsup.bot.entity.Conversation;
import com.whatsup.bot.entity.Note;
import com.whatsup.bot.repository.S3RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final Logger logger = LoggerFactory.getLogger(ConversationService.class);

    @Autowired
    S3RepositoryImpl repo;

    public void save(String phoneNumber, String lastMessage) {
        logger.info(phoneNumber + "-"+ lastMessage );
        Conversation conversation = new Conversation(phoneNumber, lastMessage);
        repo.save(Conversation.class.getSimpleName() + "/" + phoneNumber, conversation);
    }

    public Conversation get(String phoneNumber) {
        Conversation conversation = (Conversation) repo.findByKey(Conversation.class.getSimpleName() + "/" + phoneNumber + ".json", Conversation.class);
        if (conversation == null) {
            conversation = new Conversation(phoneNumber, "");
        }
        return conversation;
    }
}
