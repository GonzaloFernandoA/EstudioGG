package com.whatsup.bot.builder.task;


import com.whatsup.bot.builder.ExecuteParameter;
import com.whatsup.bot.service.ConversationService;
import com.whatsup.bot.service.EventService;
import com.whatsup.bot.service.WhatsAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskBotExecutor {

    private final Logger log = LoggerFactory.getLogger(TaskBotExecutor.class);

    private final List<ITask> tasks;

    @Autowired
    private EventService event;

    @Autowired
    WhatsAppService service;

    @Autowired
    ConversationService conversationService;

    @Autowired
    public TaskBotExecutor(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public void getConversationService(ConversationService service) {
        this.conversationService = service;
    }

    public void getWhatsAppService(WhatsAppService service) {
        this.service = service;
    }

    public void runAllTasks(ExecuteParameter parameter) {
        for (ITask task : tasks) {

            log.info("Evaluating task: {}", task.getClass().getSimpleName());
            if (task.CanRun(parameter.getLastAction() + "_" + parameter.getMessage())) {

                String message = task.getMessage(parameter);
                if (message != null) {
                    service.sendMessage(parameter.getPhoneNumber(), message);
                    log.info("Sending message: {}", message);
                } else {
                    log.info("No message to send for task: {}", task.getClass().getSimpleName());
                }
                event.saveEvent(parameter.getPhoneNumber(), task.getEventName(parameter.getPhoneNumber()));
                conversationService.save(parameter.getPhoneNumber(), task.getEventName(parameter.getPhoneNumber()));
            }
        }
    }
/*
    public void runAllTasks(String lastAction, String incomingMesage, String Name, String telefono) {

        log.info("Cantidad de tareas: {}", tasks.size());
        for (ITask task : tasks) {

            log.info("Evaluating task: {}", task.getClass().getSimpleName());
            if (task.CanRun(lastAction + "_" + incomingMesage)) {

                String message = task.getMessage(Name, telefono);
                if (message != null && !message.isEmpty()) {
                    service.sendMessage(telefono, message);
                    log.info("Sending message: {}", message);
                } else {
                    log.info("No message to send for task: {}", task.getClass().getSimpleName());
                }
                event.saveEvent(telefono, task.getEventName(telefono));
                conversationService.save(telefono, task.getEventName(telefono));
            }
        }
    }*/
}
