package com.whatsup.bot.service.agenda;

import com.whatsup.bot.repository.agendaRepository;
import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BusinessDaysCalculator {

    private agendaRepository repository;

    public void setRepository(agendaRepository repo) {
        repository = repo;
    }

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

        return repository.getOrDefault(fechaFormateada).TieneHorariosDisponibles();
    }


}
