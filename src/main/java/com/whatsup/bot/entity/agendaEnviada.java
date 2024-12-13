/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

/**
 *
 * @author Gonzalo_Avalos
 */
public class agendaEnviada {
    public String opcion;
    public String tiempo;
    
    public agendaEnviada(String opcionSugerida, String momento)
    {
        opcion = opcionSugerida; 
        tiempo = momento; 
    }
}
