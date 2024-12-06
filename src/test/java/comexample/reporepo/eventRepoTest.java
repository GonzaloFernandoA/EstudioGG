/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comexample.reporepo;

import com.whatsup.bot.entity.Evento;

import com.whatsup.bot.repository.eventRepository;
import java.io.IOException;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class eventRepoTest {
    
    @Test
    void contextLoads() {
    }

    @Test
    void getAllFromPathTest() throws IOException  {

        eventRepository repo = new eventRepository();
        List<Evento> eventos = repo.getAll("src/test/java/resources/Events/");

        Assertions.assertEquals(3, eventos.size());
        Assertions.assertEquals("dos", eventos.get(0).getTelefono());
        Assertions.assertEquals("tres", eventos.get(1).getTelefono());
        Assertions.assertEquals("uno", eventos.get(2).getTelefono());
        
        Assertions.assertEquals("Hola dos", eventos.get(0).getMensaje());
        Assertions.assertEquals("Hola tres", eventos.get(1).getMensaje());
        Assertions.assertEquals("Hola uno", eventos.get(2).getMensaje());
        
        
    }
}
