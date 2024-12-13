/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.reporepo;


import com.whatsup.bot.entity.Contacto;
import com.whatsup.bot.repository.contactosRepository;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class contactRepositoryTest {
       
    @Test
    void contextLoads() {
    }

    @Test
    void getAllFromPathTest() throws IOException  {

        contactosRepository repo = new contactosRepository();
        List<Contacto> contactos = repo.getAll("src/test/java/resources/Contactos/",Contacto.class);

        Assertions.assertEquals(1, contactos.size());
        Assertions.assertEquals("cameli", contactos.get(0).getApellido());
        Assertions.assertEquals("541145587174", contactos.get(0).getTelefono());
    }
}
