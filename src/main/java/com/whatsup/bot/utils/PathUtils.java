/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.utils;

import com.whatsup.bot.service.RobotInMesssageService;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Gonzalo_Avalos
 */
public class PathUtils {

    private static final Logger logger = LoggerFactory.getLogger(PathUtils.class);

    public static Map<String, String> readFilesToMap(String directoryPath) {
        Map<String, String> fileMap = new HashMap<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
            for (Path path : stream) {
                if (Files.isRegularFile(path)) {
                    String content = Files.readString(path);
                    fileMap.put(path.getFileName().toString().replaceAll(".json", ""), content);
                }
            }
        } catch (IOException e) {
            logger.error("Error reading files: " + e.getMessage());
        }
        return fileMap;
    }

    
    public static void move(File file, String destinationPath)  {
        if (!file.exists()) {
            logger.error("El archivo no existe." );
        }
        Path destination = Path.of(destinationPath);

        try
        {
        
        Files.move(file.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
            logger.error("Error reading files: " + e.getMessage());
        }
    }

}
