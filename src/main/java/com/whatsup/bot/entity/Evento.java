/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Evento {
    
   
    
    
        public Evento( )
    {

    }
    
    public Evento( String id, String evento)
    {
        telefono = id;
        mensaje = evento;

    }
            
    private String telefono = "";
    private String mensaje = "";

    public Evento(String telefono, String mensaje, String wa_id, String status, String fecha) {
        this.telefono = telefono;
        this.mensaje = mensaje;
        this.wa_id = wa_id;
        this.status = status;
        this.fecha = fecha;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String wa_id = "";
    private String status = "";
    private String fecha = "";

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
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
