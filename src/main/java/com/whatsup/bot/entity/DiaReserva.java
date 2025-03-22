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
 //   private String fecha; 
    private List<String> horariosOcupados = new ArrayList<>();

    /**
     * @return the fecha
     */
 /*   public String getFecha() {
        return fecha;
    }


    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
*/
    /**
     * @return the horariosDisponibles
     */
    public List<String> getHorariosOcupados() {
        return horariosOcupados;
    }

    public void setHorariosOcupados(List<String> horariosOcupados) {
        this.horariosOcupados = horariosOcupados;
    }
    
    public Boolean TieneHorariosDisponibles()
    {
        return this.getHorariosOcupados().size() < 8 ;
    }
    
}
