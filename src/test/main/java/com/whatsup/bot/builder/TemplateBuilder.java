/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.message.Video;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */

public class TemplateBuilder {

    public List<Component> Components;
    
    @Autowired
    public Video video;
    
    public List<Component> Build()
    {
        return Components;
    }
}
