package com.whatsup.bot.message;

import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.utils.IJasonable;

public class SqsMessageTurno implements IJasonable {

    private String dni;
    private String valorRespuesta;
    private String tamplte;

    public String toJson() {
        return "{\"dni\":\"" + dni + "\",\"valorRespuesta\":\"" + valorRespuesta + "\",\"tamplte\":\"" + tamplte + "\"}";
    }

    public SqsMessageTurno(LogMensajes mensaje, String responseValue) {
        this.setDni(mensaje.getId());
        this.setValorRespuesta(responseValue);
        this.setTamplte(mensaje.getCustom_id());
    }

    public SqsMessageTurno(String id, String template, String mensaje) {
        this.setDni(id);
        this.setValorRespuesta(mensaje);
        this.setTamplte(template);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getValorRespuesta() {
        return valorRespuesta;
    }

    public void setValorRespuesta(String valorRespuesta) {
        this.valorRespuesta = valorRespuesta;
    }

    public String getTamplte() {
        return tamplte;
    }

    public void setTamplte(String tamplte) {
        this.tamplte = tamplte;
    }
}
