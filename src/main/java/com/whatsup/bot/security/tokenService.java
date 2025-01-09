/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

/**
 *
 * @author Gonzalo_Avalos
 */
import com.whatsup.bot.entity.Token;
import com.whatsup.bot.repository.S3RepositoryImpl;
import com.whatsup.bot.repository.tokenRepository;
import com.whatsup.bot.utils.DateTimeUtils;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class tokenService {

    private final Logger logger = LoggerFactory.getLogger(tokenService.class);

    @Autowired
    tokenValidation validation;

    @Autowired
    tokenApiService api;

    @Autowired
    S3RepositoryImpl repo;

    public String renewToken(String temporalToken) {

        String newToken = api.Renew(temporalToken);
        repo.save("token", new Token(newToken));

        return newToken;
    }

    public String getToken() {

        Token token = (Token) repo.findByKey("token", Token.class);
        if (validation.isValid(token)) {
            return token.getSecret();
        }

        String newToken = api.Renew(token.getSecret());
        repo.save("token", new Token(newToken));

        return newToken;

    }

}
