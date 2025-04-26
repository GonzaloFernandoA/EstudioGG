/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;

import com.whatsup.bot.entity.Note;
import com.whatsup.bot.repository.S3RepositoryImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class NotaService {

    private final Logger logger = LoggerFactory.getLogger(NotaService.class);
    
    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    CarpetasConfig config;

    public void save(String telefono, String usuario, String comentario) {
        logger.info(telefono + "%"+ usuario + "%"+ comentario);
        Note nota = new Note(telefono, usuario, comentario);
        repo.save(config.getNota() + telefono, nota);
    }

    public Note get(String telefono) {
        Note entity = getOrCreate(telefono);
        return entity;
    }
    
    
    private Note getOrCreate(String key) {
        Note entity = (Note) repo.findByKey(config.getNota()+key+".json", Note.class);
        if (entity == null) {
            entity = new Note();
            entity.setId(key);
        }

        return entity;
    }
}
