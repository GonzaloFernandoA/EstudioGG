/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import com.whatsup.bot.entity.ContactoEvento;
import com.whatsup.bot.service.EventService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/**
 *
 * @author Gonzalo_Avalos
 */
@Controller
public class EventosController {
    
    @Autowired
    EventService eventService;
    
    @GetMapping("/eventos")
    public String mostrarEventos(Model model) {
        
        List<ContactoEvento> eventos = eventService.combinarListas();
        model.addAttribute("objetos", eventos );
        
        return "eventos";
    }

}
