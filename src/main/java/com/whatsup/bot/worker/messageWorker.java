/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.worker;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.WhatsAppService;
import com.whatsup.bot.utils.PathUtils;
import java.io.File;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class messageWorker {

    private Logger log = LoggerFactory.getLogger(messageWorker.class);

    
    @Autowired
    ContactConfig config;

    @Autowired
    WhatsAppService service;

    @Autowired
    EventService eventService;

   @Scheduled(fixedRate = 5000)
    public void ejecutarTareaAsincrona() {
        log.info("Tarea asíncrona ejecutándose...");

        Map<String, String> dataMap = PathUtils.readFilesToMap(config.out);
        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            log.info("Contenido encontrado " + entry.getValue());
            String firstKey = dataMap.keySet().iterator().next();
            if (entry.getValue().contains("ENVIAR_ENCUESTA")) {
                log.info("Enviando encuesta a ..." + firstKey);
                service.enviarMensajeTemplate(firstKey, null);
                eventService.saveEvent(firstKey, "ENCUESTA_ENVIADA");

            } else {
                log.info("Enviando mensaje a ..." + firstKey);
                service.sendMessage(firstKey, entry.getValue());
            }
            log.info("Borrando mensaje de salida ..." +config.out + firstKey+ ".json");
            File file = new File(config.out + firstKey + ".json");
            file.delete();
        }
    }


}
