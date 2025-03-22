/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.entity.ContactoEvento;
import com.whatsup.bot.entity.Evento;
import com.whatsup.bot.message.OutMessage;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.repository.S3RepositoryImpl;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class EventService {

    private final Logger logger = LoggerFactory.getLogger(EventService.class);

    @Autowired
    S3RepositoryImpl repo;
    
    @Autowired
    CarpetasConfig config;

    private void saveFile(String id, String evento, String path) {
            repo.save(path + id, new Evento(id, evento) );

    }

    public void saveEvent(String id, String evento) {
        this.saveFile(id, evento, config.getEventsPath());
    }

    public void saveOutMessage(String id, String evento) {
        logger.info(String.format("saveOutMessage [%s] [%s] ",  id , evento ));
        repo.save(config.getOut() + id , new OutMessage(id, evento));
       
    }

    public void saveResponse(String id, ResponseRoot evento) {
        //this.saveFile(id, evento, config.getResponse());
    }

    public List<ContactoEvento> combinarListas(List<Contacto> contactos, List<Evento> eventos) {

        Map<String, String> eventoMap = eventos.stream()
                .collect(Collectors.toMap(Evento::getTelefono, Evento::getMensaje));

        logger.info("count eventMap: " + eventoMap.size());
        logger.info("count contactos: " + contactos.size());
    
        return contactos.stream()
                .map(contacto -> new ContactoEvento(
                contacto.getNombre(),
                contacto.getApellido(),
                contacto.getTelefono(),
                eventoMap.getOrDefault(contacto.getTelefono(),null),
                        Short.valueOf("0")
                        
        ))
                .collect(Collectors.toList());
    }

    public List<ContactoEvento> combinarListas() {
        
        List<Contacto> contactos = repo.listAndReadAllAsObjects(config.getContactos(), Contacto.class);
        List<Evento> eventos = repo.listAndReadAllAsObjects(config.getEventsPath(), Evento.class);
        return this.combinarListas(contactos, eventos);
    }

}
