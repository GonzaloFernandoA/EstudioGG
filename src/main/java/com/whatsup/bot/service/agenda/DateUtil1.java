/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.service.agenda;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateUtil1 {

    public static String convertDateToText(String yyyymmdd) {
        try {
            // Parse the date from the given format
            LocalDate date = LocalDate.parse(yyyymmdd, DateTimeFormatter.ofPattern("yyyyMMdd"));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM", new Locale("es", "ES")); //, Locale.ROOT);
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            return "Invalid date format";
        }
    }

    public static LocalDate Now() {
        return LocalDate.now();
    }
        // Capitaliza la primera letra del texto (opcional, para mejorar presentación)
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
}