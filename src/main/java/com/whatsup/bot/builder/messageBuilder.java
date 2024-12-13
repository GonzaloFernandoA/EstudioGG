/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder;

import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.agenda.DateUtil1;
import com.whatsup.bot.service.trackingService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class messageBuilder {

    @Autowired
    trackingService tracking;

    @Autowired
    ReservaService service;

    public void setService(ReservaService serviceMock) {
        service = serviceMock;
    }

    public String AgendaBuild(List<String> dias) {
        List<String> diasConvert = new ArrayList<>();

        final char[] label = {'A'};

        dias.forEach(item -> {
            diasConvert.add("*" + label[0] + "*: " + DateUtil1.capitalize(DateUtil1.convertDateToText(item)));
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), diasConvert);
        return result;
    }

    public String AgendaBuildHoras(List<String> dias) {
        List<String> diasConvert = new ArrayList<>();

        final char[] label = {'A'};

        dias.forEach(item -> {
            diasConvert.add("*" + label[0] + "*: " + item);
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), diasConvert);
        return result;
    }

    public String ConfirmacionMessage(String telefono) {

        List<String> diasConvert = new ArrayList<>();
        String dia = tracking.get(telefono).getFechaReservada();
        String diaTexto = DateUtil1.convertDateToText(dia);
        String hora = tracking.get(telefono).getHoraReservada();
        String message = "Confirma el turno para el día *" + DateUtil1.capitalize(diaTexto) + "* a la hora *"
                + hora + "* ?" + System.lineSeparator() + System.lineSeparator()  ;
        
        
         List<String> opciones = new ArrayList<>();
        opciones.add("Si");
        opciones.add("No");
        
        final char[] label = {'A'};

        opciones.forEach(item -> {
            diasConvert.add("*" + label[0] + "*: " + item);
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), diasConvert);
        
        return message + result;    }
}
