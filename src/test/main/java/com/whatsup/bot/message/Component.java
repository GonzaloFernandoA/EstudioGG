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

public class Component {
    public String type = "header";
    public List<Parameter> parameters;
    
    public Component()
    {
        parameters = new ArrayList<>();
        Parameter para = new Parameter();
        parameters.add(para);
    }
}
