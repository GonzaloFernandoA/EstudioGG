/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.controler;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.repository.S3RepositoryImpl;
import com.whatsup.bot.service.ContactService;
import java.io.IOException;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gonzalo_Avalos
 */
@RestController
public class S3Controler {
    
    @Autowired
    CarpetasConfig config; 
    
    @Autowired
    ContactService service; 
    
    @Autowired
    S3RepositoryImpl repo;
    
    private Logger log = LoggerFactory.getLogger(S3Controler.class);

    @GetMapping("/saveContact")
    public ResponseEntity<String> saveContact() {
        
        Contacto contac = new Contacto("Nombre1", "Appelido", "telefono");
        
      
            repo.save(config.getContactos() + contac.getId() , contac);
           
        return new ResponseEntity<>("Hello", HttpStatus.OK);
    }
}