package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.builder.messageBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncuestaTask implements  ITask {

    private Logger log = LoggerFactory.getLogger(EncuestaTask.class);

    @Autowired
    messageBuilder builder;

    String respuesta = "";

    @Override
    public Boolean CanRun(String lasAction) {
        return

                ( lasAction.contains("MENSAJE_WELCOME_ENVIADO_1") ||
                  lasAction.contains("MENSAJE_WELCOME_ENVIADO_Si") ||
                  lasAction.contains("MENSAJE_WELCOME_ENVIADO_si") ||
                  lasAction.contains("MENSAJE_WELCOME_ENVIADO_SI")
                )
                ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {

        respuesta = parameter.getMessage().toLowerCase();
        return "Muchas gracias por su respuesta un asesor se comunicará con usted a la brevedad.";
    }

    @Override
    public String getEventName(String telefono) {
        log.info("Contacto positivo: Generating event name for {}", telefono);
        return "Llamar ahora. :"  + telefono  + " Respuesta: " + respuesta;
    }
}
