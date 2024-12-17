/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.whatsup.bot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    
        public static boolean isDifferenceLessThanNDays(Date date, int days) {
        // Calcula la diferencia absoluta en milisegundos
        Date now = new Date();

        // Calcula la diferencia absoluta en milisegundos
        long differenceInMillis = Math.abs(date.getTime() - now.getTime());

        // Convierte la diferencia a días
        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMillis);

        // Retorna si la diferencia es menor a 5 días
        return differenceInDays < days;
    }
}
