package org.khamit.travel.insurance.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public interface DateTimeService {
    public BigDecimal calculateAgreementPrice(LocalDate startDate, LocalDate endDate);
}
