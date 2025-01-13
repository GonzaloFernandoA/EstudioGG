/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import com.whatsup.bot.entity.Note;
import com.whatsup.bot.service.NotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Gonzalo_Avalos
 */
@Controller

public class NoteController {
        private final Logger logger = LoggerFactory.getLogger(NoteController.class);
        
    @Autowired
    NotaService service;
    
    @PostMapping("/notasave")
    public String saveData(@ModelAttribute Note nota ) {
        service.save(nota.getId(), nota.getUser(), nota.getNote());
        return "redirect:/eventos";
    }
    
    @GetMapping("/notas")
    public String mostrarNotas(@RequestParam String telefono , Model model) {
        Note nota = service.get(telefono);
        model.addAttribute("oNota", nota);
       
        return "notas";
    }
    
}
