/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ParameterString implements IParameter{
    public String type = "text";
    public String text = "";

    ParameterString(String nombre) {
        text = nombre; 
    }

    @Override
    public String getType() {
        return type;
    }
}
