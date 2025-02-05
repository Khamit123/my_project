package org.khamit.travel.insurance.core;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TravelPremiumUnderwritingService {

    public BigDecimal calculateUnderwriting(LocalDate dateFrom, LocalDate dateTo);
}
