/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WhatsupSecurityConfig {

    @Value("${whatsapp.cellNumber}")
    private String cellNumber;

    @Value("${whatsapp.templateName}")
    private String templateName;

    @Value("${whatsapp.templateVideo}")
    private String templateVideo;

    public String getCellNumber() {
        return cellNumber;
    }

    /**
     * @param cellNumber the cellNumber to set
     */
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName the templateName to set
     */
    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the templateVideo
     */
    public String getTemplateVideo() {
        return templateVideo;
    }

    /**
     * @param templateVideo the templateVideo to set
     */
    public void setTemplateVideo(String templateVideo) {
        this.templateVideo = templateVideo;
    }

}
