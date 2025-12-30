// src/main/java/com/whatsup/bot/controler/PropertiesController.java
package com.whatsup.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping("/config")
public class PropertiesController {

    private final ConfigurableEnvironment env;

    @Autowired
    public PropertiesController(ConfigurableEnvironment env) {
        this.env = env;
    }

    @GetMapping(value = "/properties", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> allProperties() {
        Map<String, String> props = new TreeMap<>();
        for (PropertySource<?> ps : env.getPropertySources()) {
            if (ps instanceof EnumerablePropertySource) {
                EnumerablePropertySource<?> eps = (EnumerablePropertySource<?>) ps;
                for (String name : eps.getPropertyNames()) {
                    props.putIfAbsent(name, env.getProperty(name));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : props.entrySet()) {
            sb.append(entry.getKey())
                    .append(" = ")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }

        return ResponseEntity.ok(sb.toString());
    }
}
