package com.whatsup.bot.service.agenda;

import com.whatsup.bot.config.CarpetasConfig;
import com.whatsup.bot.entity.DiaReserva;
import com.whatsup.bot.repository.S3RepositoryImpl;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BusinessDaysCalculator {

    @Autowired
    S3RepositoryImpl repository; 
    
    @Autowired
    CarpetasConfig config;
    
    public List<String> getNextBusinessDays(LocalDate today, int numberOfDays) {
        List<String> businessDays = new ArrayList<>();
        LocalDate currentDate = today;

        // Loop until we get the requested number of business days
        while (businessDays.size() < numberOfDays) {
            // Move to next day
            currentDate = currentDate.plusDays(1);

            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY
                    && this.TieneTurnosLibres(currentDate)) {

                String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                businessDays.add(formattedDate);
            }
        }
        return businessDays;
    }

    private Boolean TieneTurnosLibres(LocalDate currentDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String fechaFormateada = currentDate.format(formatter);

        DiaReserva dia = (DiaReserva)repository.findByKey(config.getReservas()+ fechaFormateada + ".json", DiaReserva.class);
        if ( dia == null)
            dia = new DiaReserva();
        
        return dia.TieneHorariosDisponibles();
    }


}
