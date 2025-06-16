package com.whatsup.bot.entity;

public class LogMensajes {

    public LogMensajes(String wa_id, String custom_id, String id) {
        this.setWa_id(wa_id);
        this.setCustom_id(custom_id);
        this.id = id;

    }

    private String wa_id;
    private String custom_id;
    private String id;

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    public String getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(String custom_id) {
        this.custom_id = custom_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
