package org.khamit.travel.insurance.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TravelPremiumUnderwritingServiceImpl implements TravelPremiumUnderwritingService {
    private final DateTimeService dateTimeService;
    public BigDecimal calculateUnderwriting(LocalDate dateFrom, LocalDate dateTo){
        return dateTimeService.calculateDaysBetween(dateFrom,dateTo);

    }

}
