package com.whatsup.bot.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatsup.bot.message.WebHookResponse.WebHookMessageWIthSender;
import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.service.LogMensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.whatsup.bot.service.ContactService;
import com.whatsup.bot.service.ConversationService;
import com.whatsup.bot.service.EquivalenciaService;

import java.util.logging.Logger;

@Component
public class ExecutorParameterBuilder {

    Logger logger = Logger.getLogger(ExecutorParameterBuilder.class.getName());

    @Autowired
    private ContactService contactService;

    @Autowired
    private ConversationService conversationService;

    @Autowired
    private EquivalenciaService equivalencia;

    @Autowired
    private LogMensajesService logMensajesService;

    /**
     * Builds an ExecuteParameter object from the incoming message.
     *
     * @param incomingMessage The incoming message as a String.
     * @return An ExecuteParameter object containing the parsed information.
     */
    @SuppressWarnings("unchecked")
    public ExecuteParameter Build(String incomingMessage)
    {
        Root message;

        if (incomingMessage.contains("Unknown")) {
            logger.info("System Message recibido: " + incomingMessage);
            return new ExecuteParameter();
        }

        WebHookMessageWIthSender messageWIthSender = new WebHookMessageWIthSender(incomingMessage);

        String telefono = equivalencia.get(messageWIthSender.getSender());
        String name = contactService.getName(telefono);
        String mensajeRecibido = messageWIthSender.getText();
        String lastAction = conversationService.get(telefono).getLastMessage();

        ExecuteParameter parameter = new ExecuteParameter(name, telefono, mensajeRecibido);
        parameter.setWaId(messageWIthSender.getWa_id());

        parameter.setLastAction(lastAction);

        return parameter;
    }
}
