/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.entity.Reserva;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gonzalo_Avalos
 */
@Service
public class ReservaService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String rutaReservas = "c:\\Cosas\\bot\\EstudioGG\\src\\test\\java\\reservas\\"; // Carpeta donde se guardan las reservas

    public List<DiaReserva> obtenerDiasDisponibles() throws IOException {
        File folder = new File(rutaReservas);
        File[] files = folder.listFiles();

        // Leer cada archivo JSON para ver días con horarios disponibles
        return files != null
                ? Arrays.stream(files)
                        .map(file -> {
                            try {
                                return objectMapper.readValue(file, DiaReserva.class);
                            } catch (IOException e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList())
                : Collections.emptyList();
    }

    public void reservarTurno(String fecha, String hora, String usuario) throws IOException {

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setFecha(fecha);
        nuevaReserva.setHora(hora);
        nuevaReserva.setUsuario(usuario);

        File file = new File(rutaReservas + usuario + ".json");
        objectMapper.writeValue(file, nuevaReserva);
    }
    
     public void reservarDiaHoraTurno(String fecha, String hora) throws IOException {

        DiaReserva diaReserva = new DiaReserva();
        diaReserva.setFecha(fecha);
      
       
     
        if (diaReserva.getHorariosOcupados().contains(hora)) {
                throw new IllegalArgumentException("Horario no disponible");
            }
        diaReserva.getHorariosOcupados().add(hora);
        
        File file = new File(rutaReservas + fecha + ".json");
        objectMapper.writeValue(file, diaReserva);

    }
}

