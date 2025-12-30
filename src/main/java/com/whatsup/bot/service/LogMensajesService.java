package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.LogMensajes;
import com.whatsup.bot.message.Metadata;
import com.whatsup.bot.repository.S3RepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import java.util.Map;


import java.util.Map;

@Service
public class LogMensajesService {

    private Logger logger = LoggerFactory.getLogger(LogMensajesService.class);

    @Autowired
    CarpetasConfig config;

    @Autowired
    S3RepositoryImpl repo;

    public void save(ResponseRoot incomingMessage, Map<String, Object> mensaje) {

            String waMessage_id = incomingMessage.messages.get(0).id;
            logger.info("SaveInconmeMessage wa_ip " + waMessage_id);
            this.save(waMessage_id, (Metadata) mensaje.get("metadata"),incomingMessage.messages.get(0).message_status);
    }

    public void save(String waMessage_id , Metadata meta, String status) {
        if( meta != null ) {
            LogMensajes mensaje = new LogMensajes(waMessage_id, meta.getCustom(), meta.getId(), status);
            repo.save(config.getMensajes() + waMessage_id, mensaje);
        }
    }

    public void save(String waMessage_id , Metadata meta) {
        if( meta != null ) {
            LogMensajes mensaje = new LogMensajes(waMessage_id, meta.getCustom(), meta.getId(), "");
            repo.save(config.getMensajes() + waMessage_id, mensaje);
        }
    }

    public LogMensajes get(String waMessage_id) {

        LogMensajes mensaje = (LogMensajes) repo.findByKey(config.getMensajes() + waMessage_id + ".json", LogMensajes.class);
       return mensaje;
    }

}
