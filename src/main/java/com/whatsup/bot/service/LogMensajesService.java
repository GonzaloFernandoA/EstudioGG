package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.message.Metadata;
import com.whatsup.bot.repository.S3RepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogMensajesService {

    @Autowired
    CarpetasConfig config;

    @Autowired
    S3RepositoryImpl repo;

    public void save(String waMessage_id , Metadata meta){
        if( meta != null ) {
            LogMensajes mensaje = new LogMensajes(waMessage_id, meta.getCustom(), meta.getId());
            repo.save(config.getMensajes() + waMessage_id, mensaje);
        }
    }

    public LogMensajes get(String waMessage_id) {

        LogMensajes mensaje = (LogMensajes) repo.findByKey(config.getMensajes() + waMessage_id + ".json", LogMensajes.class);
       return mensaje;
    }

}
