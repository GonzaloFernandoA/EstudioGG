/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.security;

import com.whatsup.bot.entity.Token;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Gonzalo_Avalos
 */
@Component
public class tokenValidation {

    @Autowired
    securityConfig config;

    public Boolean isValid(Token token) {
        long daysBetween = ChronoUnit.DAYS.between(token.getValidFrom(), LocalDate.now() );
        long daysExpired = config.getDaysBeforeRenewToken();

        return (daysBetween < daysExpired);
    }

}
