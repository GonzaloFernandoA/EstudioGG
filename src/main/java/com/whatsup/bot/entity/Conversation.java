package com.whatsup.bot.entity;

import java.time.LocalDateTime;

public class Conversation {
    private String id;
    private String phoneNumber;
    private String lastMessage;
    private String lastMessageTime;

    public Conversation( String phoneNumber, String lastMessage) {
        this.phoneNumber = phoneNumber;
        this.lastMessage = lastMessage;
        this.lastMessageTime = LocalDateTime.now().toString();
    }

    public String getId() {
        return phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(String lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

}