package org.khamit.travel.insurance.core.underwriting.calculator;

import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.DateTimeService;
import org.khamit.travel.insurance.core.repository.AgeCoefRepository;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.core.repository.InsuranceLimitRepository;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class MedicalRiskCalculator implements RiskCalculator {

    final AgeCoefRepository ageCoefRepository;
    final CountryRepository countryRepository;
    final InsuranceLimitRepository limitRepository;
    final DateTimeService dateTimeService;

    @Override
    public BigDecimal calculate(TravelCalculatePremiumRequest request) {
        Double agcoef = ageCoefRepository.findCoefByAge(dateTimeService.calculateAgeByBirthday(request.getBirthday())).getCoef();
        Double countryPremiuim = countryRepository.findByTitle(request.getCountry()).getDayPremium();
        Double limitCoef = limitRepository.findCoefByLimit(request.getInsuranceLimit().doubleValue()).getCoef();
        BigDecimal days = dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        return BigDecimal.valueOf(agcoef*countryPremiuim*limitCoef*days.doubleValue());
    }

    public String getTitle(){
        return "Медицинский риск";
    }
}
