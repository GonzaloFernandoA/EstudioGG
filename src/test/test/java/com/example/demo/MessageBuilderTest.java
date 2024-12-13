/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.builder.messageBuilder;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Gonzalo_Avalos
 */
public class MessageBuilderTest {

    @Test
    void HorarioBuildTest() throws IOException {
        messageBuilder builder = new messageBuilder();
        String horario = builder.HorarioBuild();
        Assertions.assertEquals("Jueves, 28", horario);
    }

    @Test
    void DiasBuildTest() throws IOException {
        messageBuilder builder = new messageBuilder();
        String horario = builder.AgendaBuild();
        Assertions.assertEquals("Jueves, 28", horario);
    }

}
