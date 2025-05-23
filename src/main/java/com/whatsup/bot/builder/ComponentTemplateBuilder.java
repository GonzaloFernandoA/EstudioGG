/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.message.MessageTemplateRequest;
import com.whatsup.bot.message.ComponentBody;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ComponentTemplateBuilder {
    private ComponentBody component;
            
    public ComponentTemplateBuilder(MessageTemplateRequest request )
    {
        component = new ComponentBody();
        
        
        while (!request.isEmpty()) {
             component.addParameter(request.get());
            
        }
    }
    
    public ComponentBody getComponent()
    {
        return component; 
    }
    
}
