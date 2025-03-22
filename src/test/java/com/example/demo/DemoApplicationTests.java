package com.example.demo;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.repository.S3RepositoryImpl;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.agenda.DateUtil1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

//@SpringBootTest
class DemoApplicationTests {

    @InjectMocks
    ReservaService reserva;

    @Mock
    S3RepositoryImpl repo; 
    
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void contextLoads() {
    }

    @Test
    void TextoDayInLetterTest() {

        String day = DateUtil1.convertDateToText("20241128");
        Assertions.assertEquals("jueves, 28 de noviembre", day.trim());
    }

    @Test
    void TextoAgendaTest() {
        messageBuilder builder = new messageBuilder();
        List<String> dias = new ArrayList<>();
        dias.add("20241128");
        String diasToText = builder.AgendaBuild(dias);
        Assertions.assertEquals("*A*: Jueves, 28 de noviembre", diasToText);
    }

    @Test
    void GuardarDiaReservaTest() throws IOException {

        reserva.reservarDiaHoraTurno("20241201", "15:00");
        verify(repo).save("20241201", "15:00");
      
    }

}
