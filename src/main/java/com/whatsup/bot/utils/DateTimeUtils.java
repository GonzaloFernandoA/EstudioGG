/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gonzalo_Avalos
 */
public class DateTimeUtils {
    public static String getDateTime()
    {
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        // Crear un formateador con el formato deseado
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Formatear la fecha y hora
        String fechaHoraFormateada = fechaHoraActual.format(formateador);

        // Imprimir la fecha y hora en el formato YYYYmmDDHHMMSS
        return  fechaHoraFormateada;
    }
}
