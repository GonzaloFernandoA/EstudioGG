/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message.responsePost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ResponseRoot {

    private ObjectMapper objectMapper = new ObjectMapper();

    public String messaging_product;
    public ArrayList<Contact> contacts;
    public ArrayList<Message> messages;

    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
