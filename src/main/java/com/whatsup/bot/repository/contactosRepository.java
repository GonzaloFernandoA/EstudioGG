/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.utils.JsonUtils;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class contactosRepository {

    private final Logger logger = LoggerFactory.getLogger(contactosRepository.class);

    @Autowired
    ContactConfig config;

    public void save(Contacto contacto) {
        JsonUtils.writeJsonToFile(config.contactos + contacto.getTelefono(), contacto);
    }

    public Contacto get(String telefono) {
        Contacto contacto = JsonUtils.readJsonFromFile(config.contactos + telefono, Contacto.class);

        return contacto;
    }

    public Boolean exists(String telefono) {
        Contacto contacto = this.get(telefono);
        return (contacto != null);
    }

    public List<Contacto> getAll() {
        try {
            return getAll(config.contactos, Contacto.class);
        } catch (IOException ex) {
            logger.error( ex.getMessage());
        }
        return null;
    }

    public <T> List<T> getAll(String pathDirectorio, Class<T> tipoClase) throws IOException {

        List<T> listaObjetos = new ArrayList<>();
        Path directorio = Paths.get(pathDirectorio);

        
        if (!Files.isDirectory(directorio)) {
            throw new IllegalArgumentException("El path proporcionado no es un directorio válido.");
        }

        DirectoryStream<Path> stream = Files.newDirectoryStream(directorio);

        for (Path archivo : stream) {
            if (Files.isRegularFile(archivo)) {
                String contenido = Files.readString(archivo);

                // Llamada al método estático que convierte el contenido en un objeto de la clase deseada
                T objeto = JsonUtils.readJsonFromFile(contenido, tipoClase);

                listaObjetos.add(objeto);
            }
        }

        return listaObjetos;

    }
}
