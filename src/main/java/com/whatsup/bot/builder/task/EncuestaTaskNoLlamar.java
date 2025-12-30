package com.whatsup.bot.builder.task;

import com.whatsup.bot.builder.ExecuteParameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EncuestaTaskNoLlamar implements ITask
{
    private Logger log = LoggerFactory.getLogger(EncuestaTaskNoLlamar.class);

    @Override
    public Boolean CanRun(String lastAction) {
        return
                ( lastAction.contains("MENSAJE_WELCOME_ENVIADO_2") ||
                        lastAction.contains("MENSAJE_WELCOME_ENVIADO_NO") ||
                        lastAction.contains("MENSAJE_WELCOME_ENVIADO_no") ||
                        lastAction.contains("MENSAJE_WELCOME_ENVIADO_No")
                )
                ;
    }

    @Override
    public String getMessage(ExecuteParameter parameter) {
        return "Perfecto, no te volveremos a molestar. Que tengas un buen día.";
    }

    @Override
    public String getEventName(String telefono) {
        return "FINALIZACION Contesto NO";
    }
}
