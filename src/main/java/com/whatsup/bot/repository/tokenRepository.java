/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.entity.Token;
import com.whatsup.bot.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class tokenRepository {

    @Autowired
    CarpetasConfig config;

    public Token getOrDefault() {
        Token token = JsonUtils.readJsonFromFile(config.getToken() + "tokenFile", Token.class);
        if (token == null) {
            token = new Token();
        }

        return token;
    }

    public void Post(Token token) {
        JsonUtils.writeJsonToFile(config.getToken() + "tokenFile", token);
    }

}
