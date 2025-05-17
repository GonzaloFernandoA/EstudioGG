/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ComponentBody implements IComponent {
    
    public String type = "body";
    public List<IParameter> parameters;
    
    public ComponentBody(String nombre )
    {
        parameters = new ArrayList<>();
        IParameter para = new ParameterString(nombre);
        parameters.add(para);
        
    }

    @Override
    public String getType() {
        return type;
    }
}