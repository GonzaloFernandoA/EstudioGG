/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;
    private Date fecha;

    public Contacto() {

    }
    
    @JsonIgnore
    public String getId()
    {
        return this.telefono;
    }

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fecha = null;
    }

    public Contacto(String nombre, String apellido, String telefono, Date fecha) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fecha = fecha;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
    
    public Date getFecha() {
        if (fecha == null) {
            Calendar calendario = Calendar.getInstance();
            calendario.add(Calendar.DAY_OF_MONTH, -1000);
            
            fecha = calendario.getTime();
        }
        
        return fecha;
    }
    
    public void setFecha(Date fecha) {        
        this.fecha = fecha;
    }

}
