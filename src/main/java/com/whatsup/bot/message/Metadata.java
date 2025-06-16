package com.whatsup.bot.message;

public class Metadata
{
    private String custom ;



    public Metadata() {
        this.custom = "";
        this.id = "";
    }
    public Metadata(String custom, String id) {
        this.custom = custom;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    public String getCustom() {
        return custom;
    }
    public void setCustom(String custom) {
        this.custom = custom;
    }
}
