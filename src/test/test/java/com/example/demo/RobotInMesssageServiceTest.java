/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo;

import com.whatsup.bot.service.RobotInMesssageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author Gonzalo_Avalos
 */
public class RobotInMesssageServiceTest {
    
    
     @Test
    void SaveMessageTest() 
    {
        RobotInMesssageService service = new RobotInMesssageService();
        service.SaveInconmeMessage("{\"object\":\"whatsapp_business_account\",\"entry\":[{\"id\":\"487484024449910\",\"changes\":[{\"value\":{\"messaging_product\":\"whatsapp\",\"metadata\":{\"display_phone_number\":\"15551939922\",\"phone_number_id\":\"414660938406899\"},\"contacts\":[{\"profile\":{\"name\":\"Gonza\"},\"wa_id\":\"5491145587174\"}],\"messages\":[{\"context\":{\"from\":\"15551939922\",\"id\":\"wamid.HBgNNTQ5MTE0NTU4NzE3NBUCABEYEjYyNDUyMUNDODlENDJDQkRENAA=\"},\"from\":\"5491145587174\",\"id\":\"wamid.HBgNNTQ5MTE0NTU4NzE3NBUCABIYFjNFQjBBOEQ0RkFBQjFGQjdDRjM0RTgA\",\"timestamp\":\"1733090284\",\"type\":\"button\",\"button\":{\"payload\":\"Asesoramiento Virtual\",\"text\":\"Asesoramiento Virtual\"}}]},\"field\":\"messages\"}]}]}");
                
        Assertions.assertEquals("Jueves, 28", "Jueves, 28");
    }
}
