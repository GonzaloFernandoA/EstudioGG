package com.whatsup.bot.service;

import com.whatsup.bot.config.CarpetasConfig;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.repository.S3RepositoryImpl;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    CarpetasConfig config;

    @Autowired
    S3RepositoryImpl repo;

    @Autowired
    EventService eventService;

    public void save(Contacto contacto) {

        repo.save(config.getContactos() + contacto.getId(), contacto);
    }

    public String getName(String telefono)
    {
        Contacto contac = null;
        contac = (Contacto) repo.findByKey(config.getContactos() + telefono+ ".json", Contacto.class);
        return contac.getNombre();
    }
    
    public String save(String nombre, String apellido, String telefono, RedirectAttributes redirectAttributes) {

        Contacto contac = (Contacto) repo.findByKey(config.getContactos() + telefono, Contacto.class);
        
        if (contac != null) {
            logger.error("El contacto ya existe. ");
            redirectAttributes.addFlashAttribute("alerta", "ERROR: El contacto ya existe");
            return "redirect:/contactos";
        }
        LocalDate fechaActual = LocalDate.now();
        Date fecha = Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());

        contac = new Contacto(nombre, apellido, telefono, fecha);
        this.save(contac);

        eventService.saveEvent(telefono, "CONTACTO_GUARDADO");
        eventService.saveOutMessage(telefono, "ENVIAR_ENCUESTA");
        redirectAttributes.addFlashAttribute("alerta", "El contacto se guardó con éxito");
        return "redirect:/contactos";

    }
}
