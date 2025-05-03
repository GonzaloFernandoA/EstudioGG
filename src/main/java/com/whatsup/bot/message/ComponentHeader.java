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

public class ComponentHeader implements IComponent {
    public String type = "header";
    public List<IParameter> parameters;
    
    public ComponentHeader()
    {
        this.CreateParameter();
    }

    private void CreateParameter()
    {
        parameters = new ArrayList<>();
        IParameter para = new ParameterVideo();
        parameters.add(para);   
    }

    @Override
    public String getType() {
        return type;
    }
}
