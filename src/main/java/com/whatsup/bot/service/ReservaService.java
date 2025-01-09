/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.repository.S3RepositoryImpl;

import com.whatsup.bot.service.agenda.BusinessDaysCalculator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class ReservaService {

    private final Logger log = LoggerFactory.getLogger(ReservaService.class);
    
    @Autowired
    CarpetasConfig config; 
    
    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    BusinessDaysCalculator calc;

    private List<String> getHorarios() {
        List<String> horarios = new ArrayList<>();
        horarios.add("10:00");
        horarios.add("11:00");
        horarios.add("12:00");
        horarios.add("13:00");
        horarios.add("14:00");
        horarios.add("15:00");
        horarios.add("16:00");
        horarios.add("17:00");

        return horarios;
    }
    public void reservarDiaHoraTurno(String fecha, String hora) {
        DiaReserva diaReserva = (DiaReserva)repo.findByKey(fecha, DiaReserva.class);
        diaReserva.getHorariosOcupados().add(hora);
        repo.save(config.getReservas()+ fecha, diaReserva);
    }

    public List<String> getDiasDisponibles() {
        List<String> dias = calc.getNextBusinessDays(LocalDate.now(), 5);

        return dias;
    }

    public List<String> getTurnosLibres(String dia) {
        DiaReserva diaReserva = (DiaReserva)repo.findByKey(dia, DiaReserva.class);
        return getHorasDisponibles(diaReserva.getHorariosOcupados());
    }

    private List<String> getHorasDisponibles(List<String> HorariosOcupados) {
        List<String> diferencia = new ArrayList<>(getHorarios());
        diferencia.removeAll(HorariosOcupados);
        return diferencia;
    }

    public void saveDias(String telefono, List<String> opciones) {
     //   repo.save(telefono, opciones, "DIAS");
    }
    
    public void saveHoras(String telefono, List<String> opciones) {
     //   repo.save(telefono, opciones, "HORAS");
    }

  /*  public String getDiaElegido(String telefono, String respuestaDia) {
        List<String> dias = repo.get(telefono, "DIAS");
        char lowerCaseLetter = Character.toLowerCase( respuestaDia.charAt(0));
        return dias.get(lowerCaseLetter - 'a' );

    }*/
}
