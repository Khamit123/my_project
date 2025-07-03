package org.khamit.travel.insurance.core.underwriting.calculator;

import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;

import java.math.BigDecimal;

public interface RiskCalculator {
    public BigDecimal calculate(TravelCalculatePremiumRequestV2 request, Person person);
    public String getTitle();
}
