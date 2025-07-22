/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Equivalencia;
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
public class EquivalenciaService {
    private static final Logger logger = LoggerFactory.getLogger(EquivalenciaService.class);

    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    CarpetasConfig config;
    
    public String get(String wa_id)
    {
        logger.info("Buscando: " + config.getEquivalencias()+wa_id);
        Equivalencia equivalencia = (Equivalencia)repo.findByKey(config.getEquivalencias()+wa_id + ".json", Equivalencia.class);
        equivalencia.getClass().getSimpleName();
        return equivalencia.getTelefono();
    }
    
    public void save(String wa_id, String telefono)
    {
        Equivalencia equivalencia = new Equivalencia(wa_id, telefono);
        repo.save(config.getEquivalencias() + equivalencia.getId(),equivalencia);
    }
}
