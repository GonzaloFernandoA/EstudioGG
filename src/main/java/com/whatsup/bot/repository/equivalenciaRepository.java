/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Equivalencia;
import com.whatsup.bot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class equivalenciaRepository {
    @Autowired
    CarpetasConfig config;
    
    public void save(Equivalencia equivalencia) {
        JsonUtils.writeJsonToFile(config.equivalencias + equivalencia.getWa_id(), equivalencia);
    }

    public Equivalencia get(String wa_id) {
        Equivalencia contacto = JsonUtils.readJsonFromFile(config.equivalencias + wa_id, Equivalencia.class);
        return contacto;
    }
}
