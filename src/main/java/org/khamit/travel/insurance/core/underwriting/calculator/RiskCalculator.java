package org.khamit.travel.insurance.core.underwriting.calculator;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface RiskCalculator {
    public BigDecimal calculate(TravelCalculatePremiumRequest request);
    public String getTitle();
}
