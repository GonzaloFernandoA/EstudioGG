/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message.S3;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Gonzalo_Avalos
 */
public class ResponseElements{
    @JsonProperty("x-amz-request-id") 
    public String x_amz_request_id;
    @JsonProperty("x-amz-id-2") 
    public String x_amz_id_2;
}
