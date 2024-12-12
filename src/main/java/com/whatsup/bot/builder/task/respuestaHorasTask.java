/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.trackingService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class respuestaHorasTask extends basicTask {

    @Autowired
    trackingService tracking;

    @Autowired
    EquivalenciaService equivalencia;

    @Autowired
    ReservaService reserva;

    @Autowired
    EventService event;

    public void Run(String incomingMessage) {
        Root message = this.getMessage(incomingMessage);
        String respuesta = message.entry.get(0).changes.get(0).value.messages.get(0).text.body;

        if (Valid(respuesta)) {
            String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
            String telefono = equivalencia.get(telefonowa_id);

            if (tracking.isReservaDiasIsBlank(telefono)) {
                String diaElegido = reserva.getDiaElegido(telefono, respuesta);
                event.saveOutMessage(telefono, String.format("MENU_HORA_%s", diaElegido));
                event.saveEvent(telefono, "MENU HORA");
            } else if (tracking.isReservaHorasIsBlank(telefono)) {
                    String horaElegida = tracking.getHoraSegunOpcion(telefono, respuesta);
                    tracking.saveHoraReservada(telefono, horaElegida);
                    event.saveOutMessage(telefono, "CONFIRMACION_TURNO");
            }
        }
    }

    public Boolean Valid(String respuesta) {

        Set<String> validValues = Set.of("A", "B", "C", "D", "E", "F", "G", "H",
                "a", "b", "c", "d", "e", "f", "g", "h");

        return validValues.contains(respuesta);
    }
}
