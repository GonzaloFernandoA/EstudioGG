/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ParameterVideo implements IParameter{
    public String type = "video";
    public Video video = new Video(); 

    @Override
    public String getType() {
        return type;
    }
}
