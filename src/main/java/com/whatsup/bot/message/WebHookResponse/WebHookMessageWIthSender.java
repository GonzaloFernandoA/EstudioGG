package com.whatsup.bot.message.WebHookResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WebHookMessageWIthSender {
        public String sender;
        public String text;
        public String wa_id;



    public WebHookMessageWIthSender(String sender, String text, String wa_id, String phoneNumber) {
        this.sender = sender;
        this.text = text;
        this.wa_id = wa_id;
    }

    public WebHookMessageWIthSender(String value) {
        try {
            WebHookMessageWIthSender message = new ObjectMapper().readValue(value, WebHookMessageWIthSender.class);
            this.sender = message.sender;
            this.text = message.text;
            this.wa_id = message.wa_id;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    public WebHookMessageWIthSender(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public WebHookMessageWIthSender(String sender, String text) {

        this.sender = sender;
        this.text = text;
    }

    public WebHookMessageWIthSender(String sender, String text, String wa_id) {

        this.sender = sender;
        this.text = text;
        this.wa_id = wa_id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }


    public String toString() {

        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
