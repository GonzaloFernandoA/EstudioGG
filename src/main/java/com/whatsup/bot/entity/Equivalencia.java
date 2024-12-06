/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Equivalencia {
    private String telefono;
    private String wa_id;

     public Equivalencia() {

    }
    
     public Equivalencia(String wa_id , String telefono ) {
      this.telefono = telefono;
      this.wa_id = wa_id;
    }
    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the wa_id
     */
    public String getWa_id() {
        return wa_id;
    }

    /**
     * @param wa_id the wa_id to set
     */
    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }
}
