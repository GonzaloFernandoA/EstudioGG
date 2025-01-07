/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.entity.Note;
import com.whatsup.bot.repository.noteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class NotaService {

    @Autowired
    noteRepository repo;

    public void save(String telefono, String usuario, String comentario) {
        Note nota = new Note(telefono, usuario, comentario);
        repo.save(nota);
    }

    public Note get(String telefono) {
        Note entity = repo.getOrDefault(telefono);
        return entity;
    }
}
