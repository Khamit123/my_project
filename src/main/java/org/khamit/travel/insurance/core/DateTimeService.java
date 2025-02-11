package org.khamit.travel.insurance.core;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
class DateTimeService  {


    public BigDecimal calculateDaysBetween(LocalDate startDate, LocalDate endDate) {

        return BigDecimal.valueOf(ChronoUnit.DAYS.between(startDate,endDate)+ 1L);
    }
}
