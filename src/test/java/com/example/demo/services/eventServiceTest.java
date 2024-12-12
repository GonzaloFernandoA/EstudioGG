/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.services;

import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.entity.ContactoEvento;
import com.whatsup.bot.entity.Evento;
import com.whatsup.bot.service.EventService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class eventServiceTest {

    @Test
    void contextLoads() {
    }

    @Test
    void CombinarListasTest() {
        EventService service = new EventService();

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Nombre1", "Apellido1", "TEL1"));
        contactos.add(new Contacto("Nombre2", "Apellido2", "TEL2"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("TEL1", "EVENTO1"));
        eventos.add(new Evento("TEL2", "EVENTO2"));

        List<ContactoEvento> contactosEventos = service.combinarListas(contactos, eventos);

        Assertions.assertEquals(2, contactosEventos.size());
    }

    @Test
    void CombinarListasMasContactosTest() {
        EventService service = new EventService();

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Nombre1", "Apellido1", "TEL1"));
        contactos.add(new Contacto("Nombre2", "Apellido2", "TEL2"));
        contactos.add(new Contacto("Nombre3", "Apellido3", "TEL3"));

        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("TEL1", "EVENTO1"));
        eventos.add(new Evento("TEL2", "EVENTO2"));

        List<ContactoEvento> contactosEventos = service.combinarListas(contactos, eventos);

        Assertions.assertEquals(3, contactosEventos.size());
        Assertions.assertEquals("Apellido1", contactosEventos.get(0).getApellido());
        Assertions.assertEquals("EVENTO1", contactosEventos.get(0).getEvento());
        Assertions.assertEquals("Apellido2", contactosEventos.get(1).getApellido());
        Assertions.assertEquals("EVENTO2", contactosEventos.get(1).getEvento());
        Assertions.assertEquals("Apellido3", contactosEventos.get(2).getApellido());
        Assertions.assertNull( contactosEventos.get(2).getEvento());
    }
    @Test
    void CombinarListasMasEventosTest() {
        EventService service = new EventService();

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Nombre1", "Apellido1", "TEL1"));
        contactos.add(new Contacto("Nombre2", "Apellido2", "TEL2"));
      

        List<Evento> eventos = new ArrayList<>();
        eventos.add(new Evento("TEL1", "EVENTO1"));
        eventos.add(new Evento("TEL2", "EVENTO2"));
        eventos.add(new Evento("TEL3", "EVENTO3"));
        List<ContactoEvento> contactosEventos = service.combinarListas(contactos, eventos);

        Assertions.assertEquals(2, contactosEventos.size());
        Assertions.assertEquals("Apellido1", contactosEventos.get(0).getApellido());
        Assertions.assertEquals("EVENTO1", contactosEventos.get(0).getEvento());
        Assertions.assertEquals("Apellido2", contactosEventos.get(1).getApellido());
        Assertions.assertEquals("EVENTO2", contactosEventos.get(1).getEvento());

    }

}
