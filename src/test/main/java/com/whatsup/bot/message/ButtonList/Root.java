/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message.ButtonList;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Root {
    public String messaging_product = "whatsapp";
    @JsonProperty("to") 
    public String myto;
    public String type= "interactive" ;
    public Interactive interactive = new Interactive();
}
