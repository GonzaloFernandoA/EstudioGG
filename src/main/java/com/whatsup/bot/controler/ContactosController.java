/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.whatsup.bot.service.ContactService;
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
public class ContactosController {

    @Autowired
    ContactService service;

    @GetMapping("/contactos")
    public String mostrarContactos() {
        return "contactos"; // Retorna el archivo contactos.html desde templates
    }

    @PostMapping("/save")
    public String saveData(@RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String telefono, RedirectAttributes redirectAttributes) {

        // model.addAttribute("mensajeTempotal", "Contacto guardado OK.");
        // model.addAttribute("mostrarDiv", true);
        return service.save(nombre, apellido, telefono, redirectAttributes);
    }
}
