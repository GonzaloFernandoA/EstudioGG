package com.whatsup.bot.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.config.ContactConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ContactService {
	
        private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
    
        @Autowired
        EventService eventService;
        
	@Autowired
	ContactConfig config;
	
	public String save(String nombre, String apellido, String telefono)
	{
	      String fileName = telefono + ".json";
	        File file = Paths.get(config.path, fileName).toFile();

	        if (file.exists()) {
                    logger.error("El archivo ya existe. " + file.getName());
	            redirectAttributes.addFlashAttribute("alerta", "ERROR: El contacto ya existe");
	            return "redirect:/contactos";
	        }

	        Map<String, String> data = new HashMap<>();
	        data.put("nombre", nombre);
	        data.put("apellido", apellido);
	        data.put("telefono", telefono);

	        ObjectMapper mapper = new ObjectMapper();
	        try (FileWriter writer = new FileWriter(file)) {
	            mapper.writerWithDefaultPrettyPrinter().writeValue(writer, data);
                    
	        } catch (IOException e) {
	            e.printStackTrace();
                    logger.error(e.getMessage());
	            return "redirect:/error?message=Error al guardar el archivo";
	        }
                logger.info("Archivo guardado " + file.getName());
                eventService.saveEvent(telefono, "CONTACTO_GUARDADO");
		redirectAttributes.addFlashAttribute("alerta", "El contacto se guardó con éxito");
		return "redirect:/contactos";
		
	}
}
