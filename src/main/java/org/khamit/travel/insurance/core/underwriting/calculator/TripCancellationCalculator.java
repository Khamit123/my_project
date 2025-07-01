package org.khamit.travel.insurance.core.underwriting.calculator;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TripCancellationCalculator implements RiskCalculator {
    @Override
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        return BigDecimal.TEN;
    }

    @Override
    public String getTitle() {
        return "Отмена поездки";
    }
}
