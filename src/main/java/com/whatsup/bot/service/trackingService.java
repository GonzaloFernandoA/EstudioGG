/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.repository.S3RepositoryImpl;
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
public class trackingService {
    private static final Logger logger = LoggerFactory.getLogger(trackingService.class);

    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    CarpetasConfig config; 

    public void saveFechasEnviadas(String telefono, List<String> fechas) {
        Tracking entity = new Tracking();
        entity.setFechasEnviadas(fechas);
        repo.save(config.getTracking()+telefono, entity);
    }

    public void saveFechasReservada(String telefono, String fecha) {
        Tracking entity = getOrCreate(telefono);
        entity.setFechaReservada(fecha);
        repo.save(config.getTracking()+telefono, entity);
    }

    public void saveHorasEnviadas(String telefono, List<String> horas) {
        Tracking entity = getOrCreate(telefono);
        entity.setHorasEnviadas(horas);
        repo.save(config.getTracking()+telefono, entity);
    }

    public String getFechaSegunOpcion(String telefono, String opcion) {
        Tracking entity = getOrCreate(telefono);
        logger.info("getFechaSegunOpcion : " + entity.getFechasEnviadas().size());
                
        char cOpcion = opcion.charAt(0); 
        return this.getValor(entity.getFechasEnviadas(),cOpcion );
    }

    public String getHoraSegunOpcion(Tracking tracking, char opcion) {
        return this.getValor(tracking.getHorasEnviadas(), opcion);

    }

    public String getHoraSegunOpcion(String telefono, String opcion) {

        Tracking entity = getOrCreate(telefono);
        char cOpcion = opcion.charAt(0); 
        
        return this.getValor(entity.getHorasEnviadas(), cOpcion);

    }

    private String getValor(List<String> lista, char opcion) {
        char lowerCaseLetter = Character.toLowerCase(opcion);
        return lista.get(lowerCaseLetter - 'a');
    }

    public void saveHoraReservada(String telefono, String hora) {
        Tracking entity = getOrCreate(telefono);
        entity.setHoraReservada(hora);
        repo.save(config.getTracking()+telefono, entity);
    }

    public Boolean isReservaDiasIsBlank(String telefono) {
        Tracking entity = getOrCreate(telefono);
        return entity.getFechaReservada().isEmpty();
    }

    public Boolean isReservaHorasIsBlank(String telefono) {
        Tracking entity = getOrCreate(telefono);
        return entity.getHoraReservada().isEmpty();
    }

    public Tracking get(String telefono) {
        Tracking entity = getOrCreate(telefono);
        return entity;
    }

    public Boolean IsConfirmado(String telefono) {
        Tracking entity = getOrCreate(telefono);
        return !entity.getConfirmado().isBlank();

    }

    public void Confirmar(String telefono) {
        Tracking entity = getOrCreate(telefono);
        entity.setConfirmado("OK");
        repo.save(config.getTracking()+telefono, entity);
    }

    public void Delete(String telefono) {
        Tracking entity = new Tracking();
        repo.save(config.getTracking()+telefono, entity);
    }

    private Tracking getOrCreate(String key) {
        Tracking entity = (Tracking) repo.findByKey(config.getTracking()+ key + ".json", Tracking.class);
        if (entity == null) {
            entity = new Tracking();
        }

        return entity;
    }
}
