/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ContactoEvento {
    private String nombre;
    private String apellido;
    private String evento;
    private String telefono;
    private Short hayNotas;

    public ContactoEvento (String nombre, String apellido, String telefono, String evento, Short hayNotas)
            
    {
        this.nombre = nombre; 
        this.apellido = apellido; 
        this.telefono = telefono;
        this.evento = evento;
        this.hayNotas = hayNotas;
        
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
     * @return the evento
     */
    public String getEvento() {
        return evento;
    }

    /**
     * @param evento the evento to set
     */
    public void setEvento(String evento) {
        this.evento = evento;
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
    
    public Short getHayNotas() {
        return hayNotas;
    }
    
    public void setHayNotas(Short hayNotas) {
        this.hayNotas = hayNotas;
    }
}