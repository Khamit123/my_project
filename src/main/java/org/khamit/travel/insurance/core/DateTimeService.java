package org.khamit.travel.insurance.core;

import java.math.BigDecimal;
import java.time.LocalDate;


interface DateTimeService {
    public BigDecimal calculateDaysBetween(LocalDate startDate, LocalDate endDate);
}
