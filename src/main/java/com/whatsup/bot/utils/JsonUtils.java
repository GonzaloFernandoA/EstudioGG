/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 *
 * @author Gonzalo_Avalos
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static <T> T readJsonFromFile(String file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(file + ".json"), clazz);
        } catch (IOException e) {
            logger.error("Failed to read JSON from file: " + file + "," + e.getMessage());
            return null;
        }
    }

    public static <T> List<T> readJsonFromFileToList(String file, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(new File(file + ".json"), new TypeReference<List<T>>() {} );
        } catch (IOException ex) {
            logger.error("Failed to read JSON from file: " + file + "," + ex.getMessage());
            return null;
        }
    }

    public static <T> void writeJsonToFile(String filePath, T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath + ".json"), object);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write JSON to file: " + filePath, e);
        }
        logger.info("Archivo guardado." + filePath);
    }
    
    public static <T> T JSonToClass(String cadena, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(cadena, clazz);
        } catch (IOException e) {
            logger.error("Failed to read JSON from content: " + cadena + "," + e.getMessage());
            return null;
        }
    }
}
