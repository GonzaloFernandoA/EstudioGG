package com.whatsup.bot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;

@RestController
@RequestMapping("/logs")
public class LogController {

    private static final Path LOG_PATH = Path.of("/var/log/web.stdout.log");
    private static final int DEFAULT_LINES = 100;
    private static final int MAX_LINES = 1000;

    @GetMapping(value = "/tail", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> tail(@RequestParam(name = "lines", defaultValue = "" + DEFAULT_LINES) int lines) {
        if (lines <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Parameter 'lines' must be a positive integer");
        }
        if (lines > MAX_LINES) {
            lines = MAX_LINES; // cap to avoid heavy reads
        }

        try {
            String content = readLastNLines(LOG_PATH, lines);
            return ResponseEntity.ok(content);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Log file not found: " + LOG_PATH.toString());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading log file: " + e.getMessage());
        }
    }

    private String readLastNLines(Path path, int lines) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r")) {
            long fileLength = raf.length();
            if (fileLength == 0) {
                return "";
            }

            StringBuilder result = new StringBuilder();
            StringBuilder lineBuilder = new StringBuilder();
            long pointer = fileLength - 1;
            int readLines = 0;

            while (pointer >= 0 && readLines < lines) {
                raf.seek(pointer);
                int b = raf.read();
                if (b == '\n') {
                    if (lineBuilder.length() > 0) {
                        result.insert(0, lineBuilder.reverse().toString() + System.lineSeparator());
                        lineBuilder.setLength(0);
                        readLines++;
                    } else {
                        result.insert(0, System.lineSeparator());
                        readLines++;
                    }
                } else if (b != '\r') {
                    lineBuilder.append((char) b);
                }
                pointer--;
            }

            if (readLines < lines && lineBuilder.length() > 0) {
                result.insert(0, lineBuilder.reverse().toString() + System.lineSeparator());
            }

            return result.toString();
        }
    }
}
