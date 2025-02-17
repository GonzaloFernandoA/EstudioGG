/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.RobotInMesssageService;
import com.whatsup.bot.service.agenda.DateUtil1;
import com.whatsup.bot.service.trackingService;
import com.whatsup.bot.utils.DateTimeUtils;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class respuestaHorasTask extends basicTask {

    private static final Logger logger = LoggerFactory.getLogger(respuestaHorasTask.class);

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

        String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
        String telefono = equivalencia.get(telefonowa_id);

        if (Valid(respuesta)) {

            if (tracking.isReservaDiasIsBlank(telefono)) {
                String diaElegido = tracking.getFechaSegunOpcion(telefono, respuesta);
                event.saveOutMessage(telefono, String.format("MENU_HORA_%s", diaElegido));
                tracking.saveFechasReservada(telefono, diaElegido);
                event.saveEvent(telefono, "MENU HORA");
            } else if (tracking.isReservaHorasIsBlank(telefono)) {
                String horaElegida = tracking.getHoraSegunOpcion(telefono, respuesta);
                tracking.saveHoraReservada(telefono, horaElegida);
                event.saveOutMessage(telefono, "CONFIRMACION_TURNO");
            } else if (!tracking.IsConfirmado(telefono)) {
                if ("A".equals(respuesta) || "a".equals(respuesta)) {
                    tracking.Confirmar(telefono);

                    logger.info("fecha reservada:" + tracking.get(telefono).getFechaReservada());
                    logger.info("hora reservada:" + tracking.get(telefono).getHoraReservada());
                    reserva.reservarDiaHoraTurno(tracking.get(telefono).getFechaReservada(), tracking.get(telefono).getHoraReservada());
                    event.saveEvent(telefono, "Turno Confirmado:"
                            + DateUtil1.convertDateToText(tracking.get(telefono).getFechaReservada()) + " hora " + tracking.get(telefono).getHoraReservada());

                    event.saveOutMessage(telefono, "Turno Confirmado. Gracias \uD83D\uDC4D"  );
                } else if ("B".equals(respuesta) || "b".equals(respuesta)) {
                    tracking.Delete(telefono);
                    event.saveOutMessage(telefono, "ENVIAR_ENCUESTA");
                } else {
                    event.saveOutMessage(telefono, "Respuesta inválida.");
                }

            }
        }
    }

    public Boolean Valid(String respuesta) {

        Set<String> validValues = Set.of("A", "B", "C", "D", "E", "F", "G", "H",
                "a", "b", "c", "d", "e", "f", "g", "h");

        return validValues.contains(respuesta);
    }
}
