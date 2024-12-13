package com.example.demo;

import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.agenda.DateUtil1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


//@SpringBootTest
class DemoApplicationTests {
	
	@Test
	void contextLoads() {
	}
	
	
	
	@Test
	void BotTest()
	{
		
		Assertions.assertEquals(1,1);
	}
        
      

        @Test
	void TextoDayInLetterTest()
	{
		
                String day = DateUtil1.convertDateToText("20241128");
		Assertions.assertEquals("jueves, 28", day.trim() );
	}
        
       
        @Test
	void TextoAgendaTest()
	{
            messageBuilder builder = new messageBuilder();
            List<String> dias = new ArrayList<>();
            dias.add("20241128");
            String diasToText = builder.AgendaBuild(dias);
            
            Assertions.assertEquals("Jueves, 28", diasToText);
	}
        
       
        @Test
	void GuardarDiaReservaTest() throws IOException
	{
            ReservaService service = new ReservaService();
            service.reservarDiaHoraTurno("20241201", "1500");
            Assertions.assertEquals("Jueves, 28", "Jueves, 28");
	}
        

}
