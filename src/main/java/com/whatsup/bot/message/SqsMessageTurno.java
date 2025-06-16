package com.whatsup.bot.message;

import com.whatsup.bot.entity.LogMensajes;

public class SqsMessageTurno {


    private String dni;
    private String valorRespuesta;
    private String tamplte;

    public SqsMessageTurno(LogMensajes mensaje, String responseValue) {
        this.setDni(mensaje.getId());
        this.setValorRespuesta(responseValue);
        this.setTamplte(mensaje.getCustom_id());

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
