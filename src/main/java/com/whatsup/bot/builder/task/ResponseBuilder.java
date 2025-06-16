package com.whatsup.bot.builder.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.message.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder{
    private final Logger log = LoggerFactory.getLogger(ResponseBuilder.class);

    public Root Build(String jsonResponse) {
        Root res = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the JSON string into the response object
            res = objectMapper.readValue(jsonResponse, Root.class);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
        }
        return res;
    }
}
