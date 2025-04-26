/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.message.S3;

/**
 *
 * @author Gonzalo_Avalos
 */
public class Record{
    public String eventVersion;
    public String eventSource;
    public String awsRegion;
    public String eventTime;
    public String eventName;
    public UserIdentity userIdentity;
    public RequestParameters requestParameters;
    public ResponseElements responseElements;
    public S3 s3;
}
