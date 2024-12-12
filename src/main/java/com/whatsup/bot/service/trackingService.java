/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.repository.trackingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class trackingService {

    @Autowired
    trackingRepository repo;

    public void saveFechasEnviadas(String telefono, List<String> fechas) {
        Tracking entity = repo.getOrDefault(telefono);
        entity.setFechasEnviadas(fechas);
        repo.save(telefono, entity);
    }

    public void saveFechasReservada(String telefono, String fecha) {
        Tracking entity = repo.getOrDefault(telefono);
        entity.setFechaReservada(fecha);
        repo.save(telefono, entity);
    }

    public void saveHorasEnviadas(String telefono, List<String> horas) {
        Tracking entity = repo.getOrDefault(telefono);
        entity.setHorasEnviadas(horas);
        repo.save(telefono, entity);
    }

    public String getFechaSegunOpcion(Tracking tracking, char opcion) {
        return this.getValor(tracking.getFechasEnviadas(), opcion);
    }

    public String getHoraSegunOpcion(Tracking tracking, char opcion) {
        return this.getValor(tracking.getHorasEnviadas(), opcion);

    }

    public String getHoraSegunOpcion(String telefono, String opcion) {
        Tracking entity = repo.getOrDefault(telefono);
        return this.getValor(entity.getHorasEnviadas(), opcion.charAt(0));

    }

    private String getValor(List<String> lista, char opcion) {
        char lowerCaseLetter = Character.toLowerCase(opcion);
        return lista.get(lowerCaseLetter - 'a');
    }

    public void saveHoraReservada(String telefono, String hora) {
        Tracking entity = repo.getOrDefault(telefono);
        entity.setHoraReservada(hora);
        repo.save(telefono, entity);
    }

    public Boolean isReservaDiasIsBlank(String telefono) {
        Tracking entity = repo.getOrDefault(telefono);
        return entity.getFechaReservada().isEmpty();
    }

    public Boolean isReservaHorasIsBlank(String telefono) {
        Tracking entity = repo.getOrDefault(telefono);
        return entity.getHoraReservada().isEmpty();
    }
    
    public Tracking get(String telefono) {
        Tracking entity = repo.getOrDefault(telefono);
        return entity;
    }
    
}
