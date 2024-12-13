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
public class DiaReserva {
    private String fecha; // formato YYYYMMDD
    private List<String> horariosOcupados  = new ArrayList<>(); // Ejemplo: ["10:00", "11:00", "12:00", ...]

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the horariosDisponibles
     */
    public List<String> getHorariosOcupados() {
        return horariosOcupados;
    }

    /**
     * @param horariosDisponibles the horariosDisponibles to set
     */
    public void setHorariosOcupados(List<String> horariosOcupados) {
        this.horariosOcupados = horariosOcupados;
    }
    
}
