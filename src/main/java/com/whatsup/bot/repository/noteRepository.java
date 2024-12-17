/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Equivalencia;
import com.whatsup.bot.entity.Note;
import com.whatsup.bot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class noteRepository {

    @Autowired
    CarpetasConfig config;

    public Note getOrDefault(String id) {
        Note note = JsonUtils.readJsonFromFile(config.getNota() + id, Note.class);
        if (note == null) {
            note = new Note();
        }
        return note;
    }

    public void save(Note note) {
        JsonUtils.writeJsonToFile(config.getNota() + note.getId(), note);
    }

}
