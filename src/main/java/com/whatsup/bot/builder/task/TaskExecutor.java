package com.whatsup.bot.builder.task;


import com.whatsup.bot.builder.messageBuilder;
import com.whatsup.bot.service.ConversationService;
import com.whatsup.bot.service.WhatsAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskExecutor {

    private final Logger log = LoggerFactory.getLogger(TaskExecutor.class);

    private final List<ITask> tasks;

    @Autowired
    WhatsAppService service;

    @Autowired
    ConversationService conversationService;

    @Autowired
    public TaskExecutor(List<ITask> tasks) {
        this.tasks = tasks;
    }

    public void runAllTasks(String lastAction, String incomingMesage, String Name, String telefono) {
        for (ITask task : tasks) {
            if (task.CanRun(lastAction + "_" + incomingMesage)) {
                service.sendMessage(task.getMessage(Name, telefono), telefono);
                conversationService.save(telefono,task.getEventName(telefono) );
            }
        }
    }
}
