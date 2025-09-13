package com.whatsup.bot.message.WebHookResponse;

public class WebHookMessageWIthSender {
        public String sender;
        public String text;


    public WebHookMessageWIthSender(){}

    public WebHookMessageWIthSender(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
