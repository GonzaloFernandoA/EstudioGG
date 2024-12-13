/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.worker;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.WhatsAppService;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

        Map<String, String> dataMap = readFilesToMap(config.out);

        for (Map.Entry<String, String> entry : dataMap.entrySet()) {
            log.info("Contenido encontrado " + entry.getValue());
            String firstKey = dataMap.keySet().iterator().next();
            if (entry.getValue().contains("ENVIAR_ENCUESTA")) {
                log.info("  Enviando encuesta a ..." + firstKey);
                service.enviarMensajeTemplate(firstKey, null);
                eventService.saveEvent(firstKey, "ENCUESTA_ENVIADA");
                File file = new File(config.out + firstKey);
                file.delete();
            } else {

                log.info("Enviando mensaje a ..." + firstKey);
                service.sendMessage(firstKey, entry.getValue());
            }
            File file = new File(config.out + firstKey);
            file.delete();
        }
    }

    public Map<String, String> readFilesToMap(String directoryPath) {
        Map<String, String> fileMap = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    String content = Files.readString(path);
                    fileMap.put(path.getFileName().toString(), content);
                }
            }
        } catch (IOException e) {
            log.error("Error reading files: " + e.getMessage());
        }
        return fileMap;
    }
}
