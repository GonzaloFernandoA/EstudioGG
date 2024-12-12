/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.whatsup.bot.builder.task;

import com.whatsup.bot.message.response.Root;
import com.whatsup.bot.utils.JsonUtils;


/**
 *
 * @author Gonzalo_Avalos
 */
public class basicTask implements ITask {

    public void Run() {

    }

    public Root getMessage(String incomingMessage) {

        return JsonUtils.JSonToClass(incomingMessage, Root.class);

    }

}
