/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.entity.Equivalencia;
import com.whatsup.bot.repository.equivalenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class EquivalenciaService {
    @Autowired
    equivalenciaRepository repo;
    
    public String get(String wa_id)
    {
        Equivalencia equivalencia = repo.get(wa_id);
        return equivalencia.getTelefono();
    }
    
    public void save(String wa_id, String telefono)
    {
        Equivalencia equivalencia = new Equivalencia(wa_id, telefono);
        repo.save(equivalencia);
    }
}
