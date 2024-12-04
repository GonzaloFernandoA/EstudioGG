/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.service.agenda.BusinessDaysCalculator;
import com.whatsup.bot.service.agenda.DateUtil1;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class messageBuilder {

    public String AgendaBuild() {
        List<String> dias = BusinessDaysCalculator.getNextBusinessDays(5);
        List<String> diasConvert = new ArrayList<>();

        final char[] label = {'A'};

        dias.forEach(item -> {
            diasConvert.add(label[0] + ": " + BusinessDaysCalculator.capitalize(DateUtil1.convertDateToText(item)));
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), diasConvert);
        return result;
    }

    public String HorarioBuild() {

        List<String> Horas = new ArrayList<>();
        List<String> HorasConvert = new ArrayList<>();
        Horas.add("10:00");
        Horas.add("11:00");
        Horas.add("12:00");
        Horas.add("13:00");
        Horas.add("14:00");
        Horas.add("15:00");
        Horas.add("16:00");
        Horas.add("17:00");

        final char[] label = {'1'};

        Horas.forEach(item -> {
            HorasConvert.add(label[0] + ": " + item);
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), HorasConvert);
        return result;
    }
}
