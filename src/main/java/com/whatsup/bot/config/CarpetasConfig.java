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

    @Value("${server.data.contactos}")
    private String contactos;

    @Value("${server.data.events}")
    private String eventsPath;

    @Value("${server.data.out}")
    private String out;

    @Value("${server.data.response}")
    private String response;

    @Value("${server.data.equivalencia}")
    private String equivalencias;

    @Value("${server.data.reservas}")
    private String reservas;

    @Value("${server.data.tracking}")
    private String tracking;

    @Value("${server.data.nota}")
    private String nota;

    @Value("${server.data.mensajes}")
    private String mensajes;

    @Value("${server.data.token}")
    private String token;

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

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the contactos
     */
    public String getContactos() {
        return contactos;
    }

    public void setContactos(String contactos) {
        this.contactos = contactos;
    }

    /**
     * @return the eventsPath
     */
    public String getEventsPath() {
        return eventsPath;
    }

    /**
     * @param eventsPath the eventsPath to set
     */
    public void setEventsPath(String eventsPath) {
        this.eventsPath = eventsPath;
    }

    /**
     * @return the out
     */
    public String getOut() {
        return out;
    }

    /**
     * @param out the out to set
     */
    public void setOut(String out) {
        this.out = out;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @return the equivalencias
     */
    public String getEquivalencias() {
        return equivalencias;
    }

    /**
     * @param equivalencias the equivalencias to set
     */
    public void setEquivalencias(String equivalencias) {
        this.equivalencias = equivalencias;
    }

    public String getMensajes() {
        return mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
}
