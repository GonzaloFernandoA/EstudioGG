/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.ResponseRoot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.response.Root;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ResponseEleccionOpcionTest {
    
        private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
    }
    
                    @Test
    void recepcionmensajeeleccionDiaTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/respuestaEligioDIa.json")));
        
       
        Root root = objectMapper.readValue(response, Root.class);
        Assertions.assertEquals("text", root.entry.get(0).changes.get(0).value.messages.get(0).type);
      
    }
}
