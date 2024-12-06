/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.ContactConfig;
import com.whatsup.bot.entity.Evento;

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
public class eventRepository {

    private static final Logger logger = LoggerFactory.getLogger(eventRepository.class);

    @Autowired
    ContactConfig config;

    public List<Evento> getAll(String srctestjavaresourcesEvents) throws IOException {
        List<Evento> listaArchivos = new ArrayList<>();
        Path directorio = Paths.get(srctestjavaresourcesEvents);

        if (!Files.isDirectory(directorio)) {
            throw new IllegalArgumentException("El path proporcionado no es un directorio válido.");
        }

        DirectoryStream<Path> stream = Files.newDirectoryStream(directorio);

        for (Path archivo : stream) {
            if (Files.isRegularFile(archivo)) {
                String nombreArchivo = archivo.getFileName().toString();
                String id = nombreArchivo.contains(".")
                        ? nombreArchivo.substring(0, nombreArchivo.lastIndexOf('.'))
                        : nombreArchivo;
                String contenido = Files.readString(archivo);

                listaArchivos.add(new Evento(id, contenido));
            }
        }

        return listaArchivos;

    }

    public List<Evento> getAll() {
        try {
            return getAll(config.eventsPath);
        } catch (IOException ex) {
            return null;
        }
    }
}
