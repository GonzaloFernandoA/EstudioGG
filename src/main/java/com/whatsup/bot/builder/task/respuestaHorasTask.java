/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.service.EquivalenciaService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.ReservaService;
import com.whatsup.bot.service.agenda.DateUtil1;
import com.whatsup.bot.service.trackingService;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
    
    @Autowired
    ConfirmacionTurnoTask turnoTask;

    public void Run(String incomingMessage) {
        Root message = this.getMessage(incomingMessage);
        String respuesta = message.entry.get(0).changes.get(0).value.messages.get(0).text.body;
        String telefonowa_id = message.entry.get(0).changes.get(0).value.contacts.get(0).wa_id;
        
        String telefono = equivalencia.get(telefonowa_id);
        logger.info("Respuesta before valid [" + respuesta + "]");
        if (Valid(respuesta)) {
            logger.info("Respuesta Valida");
            if (tracking.isReservaDiasIsBlank(telefono)) {
                logger.info("Respuesta Valida 2");
                String diaElegido = tracking.getFechaSegunOpcion(telefono, respuesta);
                event.saveOutMessage(telefono, String.format("MENU_HORA_%s", diaElegido));
                tracking.saveFechasReservada(telefono, diaElegido);
                event.saveEvent(telefono, "MENU HORA");
                logger.info("Respuesta Valida");
            } else if (tracking.isReservaHorasIsBlank(telefono)) {
                logger.info("isReservaHorasIsBlank");
                String horaElegida = tracking.getHoraSegunOpcion(telefono, respuesta);
                tracking.saveHoraReservada(telefono, horaElegida);
                event.saveOutMessage(telefono, "CONFIRMACION_TURNO");
            } else if (!tracking.IsConfirmado(telefono)) {
                if ("A".equals(respuesta) || "a".equals(respuesta)) {
                    logger.info("IsConfirmado");
                    logger.info("fecha reservada:" + tracking.get(telefono).getFechaReservada());
                    logger.info("hora reservada:" + tracking.get(telefono).getHoraReservada());
                    tracking.Confirmar(telefono);
                    reserva.reservarDiaHoraTurno(tracking.get(telefono).getFechaReservada(), tracking.get(telefono).getHoraReservada());
                    event.saveEvent(telefono, "Turno Confirmado:"
                            + DateUtil1.convertDateToText(tracking.get(telefono).getFechaReservada()) + " hora " + tracking.get(telefono).getHoraReservada());

                    event.saveOutMessage(telefono, "Turno Confirmado. Muchas gracias !!!" ) ; //turnoTask.CreateMessage(telefono) );
                } else if ("B".equals(respuesta) || "b".equals(respuesta)) {
                    tracking.Delete(telefono);
                    event.saveOutMessage(telefono, "ENVIAR_ENCUESTA");
                } else {
                    event.saveOutMessage(telefono, "Respuesta inválida.");
                }

            }
        } else {logger.info("Respuesta Invalida");}
    }

    public Boolean Valid(String respuesta) {

        Set<String> validValues = Set.of("A", "B", "C", "D", "E", "F", "G", "H",
                "a", "b", "c", "d", "e", "f", "g", "h");

        return validValues.contains(respuesta);
    }
}
