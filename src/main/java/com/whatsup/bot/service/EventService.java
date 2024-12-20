/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.entity.ContactoEvento;
import com.whatsup.bot.entity.Evento;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import com.whatsup.bot.repository.contactosRepository;
import com.whatsup.bot.repository.eventRepository;
import com.whatsup.bot.utils.JsonUtils;
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
    ContactConfig config;

    @Autowired
    contactosRepository contactRepo;

    @Autowired
    eventRepository eventRepo;

    private void saveFile(String id, Object evento, String path) {
        JsonUtils.writeJsonToFile(path + id, evento);
    }

    public void saveEvent(String id, String evento) {
        this.saveFile(id, evento, config.eventsPath);
    }

    public void saveOutMessage(String id, String evento) {
        this.saveFile(id, evento, config.out);
    }

    public void saveResponse(String id, ResponseRoot evento) {

        this.saveFile(id, evento, config.response);

    }

    public List<ContactoEvento> combinarListas(List<Contacto> contactos, List<Evento> eventos) {

       
        Map<String, String> eventoMap = eventos.stream()
                .collect(Collectors.toMap(Evento::getTelefono, Evento::getMensaje));

        logger.info("count eventMap: " + eventoMap.size());
        logger.info("count contactos: " + contactos.size());
    
   //       .filter(contacto -> eventoMap.containsKey(contacto.getTelefono())) // Filtrar contactos con eventos
        
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
        List<Contacto> contactos = contactRepo.getAll();
        List<Evento> eventos = eventRepo.getAll();
        return this.combinarListas(contactos, eventos);

    }

}
