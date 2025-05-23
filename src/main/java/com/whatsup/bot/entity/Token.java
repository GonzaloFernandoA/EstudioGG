/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.entity;

import java.time.LocalDate;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Token {
    private String secret; 
    private LocalDate validFrom;

    
    public Token(){}
    
    public Token(String newToken )
            
    {
        this.secret = newToken; 
        this.validFrom = LocalDate.now();
    }
    
    /**
     * @return the secret
     */
    public String getSecret() {
        return secret;
    }

    /**
     * @param secret the secret to set
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * @return the validFrom
     */
    public LocalDate getValidFrom() {
        return validFrom;
    }

    /**
     * @param validFrom the validFrom to set
     */
    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }
    
}
