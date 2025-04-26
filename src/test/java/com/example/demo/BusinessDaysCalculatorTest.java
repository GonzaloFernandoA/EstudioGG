/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.repository.S3RepositoryImpl;
import com.whatsup.bot.service.agenda.BusinessDaysCalculator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
/**
 *
 * @author Gonzalo_Avalos
 */
public class BusinessDaysCalculatorTest {

    @InjectMocks
    BusinessDaysCalculator calc; 
    
    @Mock
    S3RepositoryImpl repo;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TodosLosDiasLibresTest() {

        when(repo.findByKey("turnos/20241209", any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241209", any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241209", any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241209", any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241209", any())).thenReturn(new DiaReserva());   
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fecha = LocalDate.parse("20241207", formatter);
        List<String> diasDisponibles = calc.getNextBusinessDays(fecha,5);
        
        Assertions.assertEquals(5, diasDisponibles.size());
        Assertions.assertEquals("20241213", diasDisponibles.get(4));
    }
    
    
    @Test
    void TodosLosDiasLibresExcepto1Test() {

        DiaReserva diaFull = new DiaReserva();   
        diaFull.getHorariosOcupados().add("1");
        diaFull.getHorariosOcupados().add("2");
        diaFull.getHorariosOcupados().add("3");
        diaFull.getHorariosOcupados().add("4");
        diaFull.getHorariosOcupados().add("5");
        diaFull.getHorariosOcupados().add("6");
        diaFull.getHorariosOcupados().add("7");
        diaFull.getHorariosOcupados().add("8");
        
        when(repo.findByKey("turnos/20241209",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241210",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241211",any())).thenReturn(diaFull);   
        when(repo.findByKey("turnos/20241212",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241213",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241216",any())).thenReturn(new DiaReserva());   
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fecha = LocalDate.parse("20241207", formatter);
        List<String> diasDisponibles = calc.getNextBusinessDays(fecha,5);
        
        Assertions.assertEquals(5, diasDisponibles.size());
        Assertions.assertEquals("20241216", diasDisponibles.get(4));
    }
    
    
        @Test
    void TodosLosDiasLibresExcepto3Test() {

        DiaReserva diaFull = new DiaReserva();   
        diaFull.getHorariosOcupados().add("1");
        diaFull.getHorariosOcupados().add("2");
        diaFull.getHorariosOcupados().add("3");
        diaFull.getHorariosOcupados().add("4");
        diaFull.getHorariosOcupados().add("5");
        diaFull.getHorariosOcupados().add("6");
        diaFull.getHorariosOcupados().add("7");
        diaFull.getHorariosOcupados().add("8");
        
        when(repo.findByKey("turnos/20241209",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241210",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241211",any())).thenReturn(diaFull);   
        when(repo.findByKey("turnos/20241212",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241213",any())).thenReturn(new DiaReserva());   
        when(repo.findByKey("turnos/20241216",any())).thenReturn(diaFull); 
        when(repo.findByKey("turnos/20241217",any())).thenReturn(diaFull); 
        when(repo.findByKey("turnos/20241218",any())).thenReturn(new DiaReserva());   
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate fecha = LocalDate.parse("20241207", formatter);
        List<String> diasDisponibles = calc.getNextBusinessDays(fecha,5);
        
        Assertions.assertEquals(5, diasDisponibles.size());
        Assertions.assertEquals("20241209", diasDisponibles.get(0));
        Assertions.assertEquals("20241218", diasDisponibles.get(4));
    }
}
