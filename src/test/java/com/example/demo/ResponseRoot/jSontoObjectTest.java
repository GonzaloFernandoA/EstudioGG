/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.ResponseRoot;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.message.WebHookResponse.WebHookResponseRoot;
import com.whatsup.bot.message.responsePost.ResponseRoot;
import java.io.IOException;

/**
 *
 * @author Gonzalo_Avalos
 */
public class jSontoObjectTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void contextLoads() {
    }

    @Test
    void BotTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/PostResponse.json")));
        ResponseRoot root = objectMapper.readValue(response, ResponseRoot.class);

        Assertions.assertEquals("whatsapp", root.messaging_product);
        Assertions.assertEquals("wamid.HBgNNTQ5MTE0NTU4NzE3NBUCABEYEjlFQTFFNzBDNzg0MjYwODU4NgA=", root.messages.get(0).id);
    }
    
        @Test
    void WebHookTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/WebHookResponseRoot.json")));
        WebHookResponseRoot root = objectMapper.readValue(response, WebHookResponseRoot.class);

        Assertions.assertEquals("whatsapp_business_account", root.object ) ;
      //  Assertions.assertEquals("wamid.HBgNNTQ5MTE0NTU4NzE3NBUCABEYEjlFQTFFNzBDNzg0MjYwODU4NgA=", root.messages.get(0).id);
    }
    
            @Test
    void NoExpirationTimeStampTest() throws IOException {

        String response = new String(Files.readAllBytes(Paths.get("src/test/java/resources/NoexiraptionTimeStamp.json")));
        WebHookResponseRoot root = objectMapper.readValue(response, WebHookResponseRoot.class);
        Assertions.assertEquals("whatsapp_business_account", root.object ) ;
        Assertions.assertNull(root.entry.get(0).changes.get(0).value.statuses.get(0).conversation.expiration_timestamp);
    }
}
