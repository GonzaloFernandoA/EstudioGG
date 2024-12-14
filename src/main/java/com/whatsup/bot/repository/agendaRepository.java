/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.utils.JsonUtils;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class agendaRepository {

    private final Logger logger = LoggerFactory.getLogger(agendaRepository.class);

    @Autowired
    CarpetasConfig config;

    public DiaReserva getOrDefault(String dia) {
        DiaReserva diaReserva = JsonUtils.readJsonFromFile(config.getReservas() + dia, DiaReserva.class);
        if (diaReserva == null) {
            diaReserva = new DiaReserva();
        }

        return diaReserva;
    }

    public void save(String dia, String hora) {
        DiaReserva diaReserva = getOrDefault(dia);
        diaReserva.getHorariosOcupados().add(hora);
        JsonUtils.writeJsonToFile(config.getReservas() + dia, diaReserva);
    }

    public void save(String telefono, List<String> agendaEnviada, String escala) {
        JsonUtils.writeJsonToFile(config.getReservas() + telefono + "_" + escala, agendaEnviada);
    }

    public List<String> get(String telefono, String escala) {
        return JsonUtils.readJsonFromFileToList(config.getReservas() + telefono + "_" + escala, String.class);
    }
}