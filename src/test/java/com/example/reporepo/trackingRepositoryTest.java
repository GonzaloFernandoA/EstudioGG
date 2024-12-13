/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporepo;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.repository.trackingRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class trackingRepositoryTest {

    @InjectMocks
    trackingRepository repo;

    @Mock
    CarpetasConfig config;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getEmptyTrackTest() {
        when(config.getTracking()).thenReturn("src/test/java/resources/tracking/");
        Tracking track = repo.getOrDefault("NOAHYRESERVA");
        Assertions.assertEquals(0, track.getFechasEnviadas().size());
        Assertions.assertEquals(0, track.getHorasEnviadas().size());
    }

    @Test
    void postSaveFullTrackTest() {
        when(config.getTracking()).thenReturn("src/test/java/resources/tracking/");
        Tracking track = repo.getOrDefault("NOAHYRESERVA");

        track.setFechaReservada("20241012");
        track.setHoraReservada("12:00");
        List<String> fechasEnviadas = new ArrayList<>();
        fechasEnviadas.add("20241012");
        fechasEnviadas.add("20241013");
        fechasEnviadas.add("20241014");
        track.setFechasEnviadas(fechasEnviadas);

        List<String> horasEnviadas = new ArrayList<>();
        horasEnviadas.add("10:00");
        horasEnviadas.add("11:00");
        horasEnviadas.add("12:00");

        track.setHorasEnviadas(horasEnviadas);
        repo.save("telefono1", track);

    }

    @Test
    void getSaveFullTrackTest() {
        when(config.getTracking()).thenReturn("src/test/java/resources/tracking/");
        Tracking track = repo.getOrDefault("telefono1");

        Assertions.assertEquals(3, track.getFechasEnviadas().size());
        Assertions.assertEquals(3, track.getHorasEnviadas().size());
        Assertions.assertEquals("12:00", track.getHorasEnviadas().get(2));
    }
}
