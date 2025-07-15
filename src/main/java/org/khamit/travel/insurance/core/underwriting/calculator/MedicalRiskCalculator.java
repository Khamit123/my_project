package org.khamit.travel.insurance.core.underwriting.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.khamit.travel.insurance.core.service.DateTimeService;
import org.khamit.travel.insurance.core.repository.AgeCoefRepository;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.core.repository.InsuranceLimitRepository;
import org.khamit.travel.insurance.dto.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
@Setter
@Getter
public class MedicalRiskCalculator implements RiskCalculator {

    final AgeCoefRepository ageCoefRepository;
    final CountryRepository countryRepository;
    final InsuranceLimitRepository limitRepository;
    final DateTimeService dateTimeService;
    @Value("${medical.risk.limit.level.enabled}")
    private Boolean limitEnabled;
    @Value("${medical.risk.ageCoef.enabled}")
    private Boolean ageCoefEnabled;

    @Override
    public BigDecimal calculate(TravelCalculatePremiumRequestV2 request, Person person) {
        Double agcoef = ageCoefEnabled ?
                ageCoefRepository.findCoefByAge(dateTimeService.calculateAgeByBirthday(person.getBirthday())).getCoef()
                :1d;
        Double countryPremiuim = countryRepository.findByTitle(request.getCountry()).getDayPremium();
        Double limitCoef = limitEnabled?
                limitRepository.findCoefByLimit(person.getMedicalLimit().doubleValue()).getCoef()
                :1d;
        BigDecimal days = dateTimeService.calculateDaysBetween(request.getAgreementDateFrom(),request.getAgreementDateTo());
        return BigDecimal.valueOf(agcoef*countryPremiuim*limitCoef*days.doubleValue()).setScale(2, RoundingMode.HALF_UP);
    }

    public String getTitle(){
        return "Медицинский риск";
    }
}
