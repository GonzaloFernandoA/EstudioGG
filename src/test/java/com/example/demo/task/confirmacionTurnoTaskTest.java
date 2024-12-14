/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.task;

import com.whatsup.bot.builder.task.ConfirmacionTurnoTask;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Gonzalo_Avalos
 */
public class confirmacionTurnoTaskTest {

    @InjectMocks
    ConfirmacionTurnoTask task;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validTest() throws IOException {

        Assertions.assertTrue(task.IsValid("A"));

    }

    @Test
    void valid2Test() throws IOException {

        Assertions.assertTrue(task.IsValid("B"));

    }
    
        @Test
    void valid2Testa() throws IOException {

        Assertions.assertTrue(task.IsValid("a"));

    }
    
        @Test
    void valid2Testb() throws IOException {

        Assertions.assertTrue(task.IsValid("b"));

    }

    @Test
    void NotValid2Test() throws IOException {

        Assertions.assertFalse(task.IsValid("0"));
    }
}
