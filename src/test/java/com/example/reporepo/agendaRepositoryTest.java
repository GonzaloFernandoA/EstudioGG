/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporepo;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.repository.agendaRepository;
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
public class agendaRepositoryTest {

    @InjectMocks
    agendaRepository repo;

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
    void getEmptyDayTest() {
        when(config.getReservas()).thenReturn("src/test/java/resources/agenda/");
        DiaReserva dia = repo.getOrDefault("NOAHYRESERVA");
        Assertions.assertEquals(0, dia.getHorariosOcupados().size());
    }

    @Test
    void saveAgendaEnviadaTest() {
        when(config.getReservas()).thenReturn("src/test/java/resources/agenda/");
        List<String> agenda = new ArrayList<>();
        agenda.add("HORA1");
        agenda.add("HORA2");

        repo.save("111111", agenda, "HORA");
        Assertions.assertEquals(0, 0);
    }

    @Test
    void getDiasEnviadosTest() {
        when(config.getReservas()).thenReturn("src/test/java/resources/agenda/");
        
        List<String> dias = repo.get("541145587174","DIAS");
        Assertions.assertEquals(5, dias.size());
        
        Assertions.assertEquals("20241210", dias.get(0));
        Assertions.assertEquals("20241216", dias.get(4));
    }
}
