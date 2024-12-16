/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Gonzalo_Avalos
 */
@Configuration
public class CarpetasConfig {

    @Value("${server.data.equivalencia}")
    public String equivalencias;

    @Value("${server.data.reservas}")
    private String reservas;

    @Value("${server.data.tracking}")
    private String tracking;
    
    @Value("${server.data.nota}")
    private String nota;
    
    /**
     * @return the reservas
     */
    public String getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(String reservas) {
        this.reservas = reservas;
    }

    /**
     * @return the tracking
     */
    public String getTracking() {
        return tracking;
    }

    /**
     * @param tracking the tracking to set
     */
    public void setTracking(String tracking) {
        this.tracking = tracking;
    }

    /**
     * @return the nota
     */
    public String getNota() {
        return nota;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(String nota) {
        this.nota = nota;
    }
}
