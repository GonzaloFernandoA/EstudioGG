/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gonzalo_Avalos
 */
public class MessageTemplateRequest {
    
    private String type = "template";
    private final Queue<String> parametros = new LinkedList<>();
    private String template; 
    private String telefono; 

    public String toJson()
    {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(MessageTemplateRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void add(String valor)
    {
        parametros.add(valor);
    }
    
    public boolean isEmpty()
    {
        return this.parametros.isEmpty();
    }
    
    public String get()
    {
        return this.parametros.poll();
    }

    /**
     * @return the template
     */
    public String getTemplate() {
        return template;
    }

    /**
     * @param template the template to set
     */
    public void setTemplate(String template) {
        this.template = template;
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
}
