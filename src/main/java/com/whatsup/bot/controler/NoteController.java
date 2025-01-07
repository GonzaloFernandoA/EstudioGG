/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import com.whatsup.bot.entity.Note;
import com.whatsup.bot.service.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
/**
 *
 * @author Gonzalo_Avalos
 */
@Controller

public class NoteController {
    
        @Autowired
    NotaService service;
    
    @PostMapping("/nota/save")
    public void saveData(@RequestParam String telefono,
            @RequestParam String usuario,
            @RequestParam String nota) {

        service.save(telefono, usuario, nota);  
    }
    
    
    @GetMapping("/nota/[idTelefono]")
    public Note saveData(@RequestParam String telefono ) {

        Note entity = service.get(telefono);
        return entity;
        
       
    }
}
