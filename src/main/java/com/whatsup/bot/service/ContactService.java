package com.whatsup.bot.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.repository.contactosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    contactosRepository repo;

    @Autowired
    EventService eventService;

    public String save(String nombre, String apellido, String telefono, RedirectAttributes redirectAttributes) {
        if (repo.exists(telefono)) {
            logger.error("El contacto ya existe. ");
            return "redirect:/error?message=El archivo ya existe";
        }

        Contacto contacto = new Contacto(nombre, apellido, telefono);
        repo.save(contacto);

        eventService.saveEvent(telefono, "CONTACTO_GUARDADO");
        eventService.saveOutMessage(telefono, "ENVIAR_ENCUESTA");
        redirectAttributes.addFlashAttribute("alerta", "El contacto se guardó con éxito");
        return "redirect:/contactos";

    }
}
