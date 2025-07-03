package org.khamit.travel.insurance.core.underwriting.calculator;

import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TripCancellationCalculator implements RiskCalculator {
    @Override
    public BigDecimal calculate(TravelCalculatePremiumRequestV2 request, Person person) {
        return BigDecimal.TEN;
    }

    @Override
    public String getTitle() {
        return "Отмена поездки";
    }
}
