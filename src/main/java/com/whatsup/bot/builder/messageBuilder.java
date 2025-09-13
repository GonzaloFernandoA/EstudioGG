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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gonzalo_Avalos
 */
@Component
public class messageBuilder {

    private final Logger log = LoggerFactory.getLogger(messageBuilder.class);

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

        return String.join(System.lineSeparator(), diasConvert);
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

    public String getAsesoramientoVirtualMessage() {
        return "Muchas Gracias. Nos comunicaremos con usted a la brevedad.";
    }

    public String construirWelcomeMensaje(String nombre) {
        return "Hola " + nombre + ", 🙋🏻soy Martín del estudio Guiggi y Ortiz, especialistas en accidentes de tránsito. Estoy aquí para asesorarte legalmente. 🤗 ¿Querés que charlemos brevemente sin compromiso?";
    }

    public String construirMensajeOpciones(String nombre) {
        return "¡Gracias por tu respuesta, " + nombre + "! Para poder ayudarte mejor, ¿podés elegir una de estas opciones?\n\n"
                + "1️⃣ Sí, quiero ir al estudio\n"
                + "2️⃣ Prefiero una reunión virtual\n"
                + "3️⃣ Por el momento no necesito asesoría\n\n"
                + "Así coordinamos lo que te quede más cómodo 😊";
    }

    public String ConfirmacionMessage(String telefono) {

        List<String> diasConvert = new ArrayList<>();
        String dia = tracking.get(telefono).getFechaReservada();
        String diaTexto = DateUtil1.convertDateToText(dia);
        String hora = tracking.get(telefono).getHoraReservada();
        String message = "Confirma el turno para el día *" + DateUtil1.capitalize(diaTexto) + "* a la hora *"
                + hora + "* ?" + System.lineSeparator() + System.lineSeparator();


        List<String> opciones = new ArrayList<>();
        opciones.add("Si");
        opciones.add("No");

        final char[] label = {'A'};

        opciones.forEach(item -> {
            diasConvert.add("*" + label[0] + "*: " + item);
            label[0]++;
        });

        String result = String.join(System.lineSeparator(), diasConvert);

        return message + result;
    }
}
