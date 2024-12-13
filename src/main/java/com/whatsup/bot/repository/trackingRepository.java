/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.repository;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.Tracking;
import com.whatsup.bot.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gonzalo_Avalos
 */
@Repository
public class trackingRepository {

    private final Logger logger = LoggerFactory.getLogger(trackingRepository.class);

    @Autowired
    CarpetasConfig config;

    public Tracking getOrDefault(String telefono) {

        Tracking tracking = JsonUtils.readJsonFromFile(config.getTracking() + telefono, Tracking.class);
        if (tracking == null) {
            tracking = new Tracking();
        }

        return tracking;
    }

    public void save(String telefono, Tracking tracking) {
        JsonUtils.writeJsonToFile(config.getTracking() + telefono, tracking);
    }
}
