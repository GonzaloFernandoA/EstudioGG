/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Tracking {

    private List<String> fechasEnviadas = new ArrayList<>();
    private String fechaReservada = "";
    private List<String> horasEnviadas = new ArrayList<>();
    private String horaReservada = "";

    /**
     * @return the fechasEnviadas
     */
    public List<String> getFechasEnviadas() {
        return fechasEnviadas;
    }

    /**
     * @param fechasEnviadas the fechasEnviadas to set
     */
    public void setFechasEnviadas(List<String> fechasEnviadas) {
        this.fechasEnviadas = fechasEnviadas;
    }

    /**
     * @return the fechaReservada
     */
    public String getFechaReservada() {
        return fechaReservada;
    }

    /**
     * @param fechaReservada the fechaReservada to set
     */
    public void setFechaReservada(String fechaReservada) {
        this.fechaReservada = fechaReservada;
    }

    /**
     * @return the horasEnviadas
     */
    public List<String> getHorasEnviadas() {
        return horasEnviadas;
    }

    /**
     * @param horasEnviadas the horasEnviadas to set
     */
    public void setHorasEnviadas(List<String> horasEnviadas) {
        this.horasEnviadas = horasEnviadas;
    }

    /**
     * @return the horaReservada
     */
    public String getHoraReservada() {
        return horaReservada;
    }

    /**
     * @param horaReservada the horaReservada to set
     */
    public void setHoraReservada(String horaReservada) {
        this.horaReservada = horaReservada;
    }
}
