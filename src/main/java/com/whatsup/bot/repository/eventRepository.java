/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class eventRepository {

    /*
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
    }*/
}
