package com.whatsup.bot.service.agenda;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BusinessDaysCalculator {

 public static List<String> getNextBusinessDays(int numberOfDays) {
        List<String> businessDays = new ArrayList<>();
        LocalDate currentDate = DateUtil1.Now();

        // Loop until we get the requested number of business days
        while (businessDays.size() < numberOfDays) {
            // Move to next day
            currentDate = currentDate.plusDays(1);

            // Check if the day is a weekday
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                // Format the date as YYYYMMDD
                String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                businessDays.add(formattedDate);
            }
        }
        return businessDays;
    }

    // Capitaliza la primera letra del texto (opcional, para mejorar presentación)
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}
