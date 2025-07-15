package org.khamit.travel.insurance.core.underwriting.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.service.DateTimeService;
import org.khamit.travel.insurance.core.domain.AgeCoef;
import org.khamit.travel.insurance.core.domain.Country;
import org.khamit.travel.insurance.core.domain.InsuranceLimit;
import org.khamit.travel.insurance.core.repository.AgeCoefRepository;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.core.repository.InsuranceLimitRepository;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MedicalRiskCalculatorTest {
    @Mock
    AgeCoefRepository ageCoefRepository;
    @Mock
    CountryRepository countryRepository;
    @Mock
    InsuranceLimitRepository limitRepository;
    @Mock
    DateTimeService dateTimeService;
    @InjectMocks
    MedicalRiskCalculator medicalRiskCalculator;

    @Test
    void calculateWorkCorrectlyIflimitEnabledAndAgeEnbaled() {
        TravelCalculatePremiumRequestV2 request =UtillMethods.createRequestV2();
        medicalRiskCalculator.setLimitEnabled(true);
        medicalRiskCalculator.setAgeCoefEnabled(true);
        Mockito.when(dateTimeService.calculateAgeByBirthday(request.getPersonList().getFirst().getBirthday())).thenReturn(22);
        Mockito.when(ageCoefRepository.findCoefByAge(22)).thenReturn(new AgeCoef(3L, 18, 26, 1.2d));
        Mockito.when(countryRepository.findByTitle("Россия")).thenReturn(new Country(1L, "Россия", 100d));
        Mockito.when(limitRepository.findCoefByLimit(request.getPersonList().getFirst().getMedicalLimit().doubleValue())).
                thenReturn(new InsuranceLimit(1L, 0d, 1000d, 1.4d));
        Mockito.when(dateTimeService.calculateDaysBetween
                (LocalDate.now().plusDays(1), LocalDate.now().plusDays(6))).thenReturn(BigDecimal.valueOf(6));
        assertEquals(0, BigDecimal.valueOf(1008).compareTo(medicalRiskCalculator.calculate(request,request.getPersonList().getFirst())));

    }


    @Test
    void calculateWorkCorrectlyIflimitEnabledAndAgeDisabled() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        medicalRiskCalculator.setLimitEnabled(true);
        medicalRiskCalculator.setAgeCoefEnabled(false);

        Mockito.when(countryRepository.findByTitle("Россия")).thenReturn(new Country(1L, "Россия", 100d));
        Mockito.when(limitRepository.findCoefByLimit(request.getPersonList().getFirst().getMedicalLimit().doubleValue())).
                thenReturn(new InsuranceLimit(1L, 0d, 1000d, 1.4d));
        Mockito.when(dateTimeService.calculateDaysBetween
                (LocalDate.now().plusDays(1), LocalDate.now().plusDays(6))).thenReturn(BigDecimal.valueOf(6));
        assertEquals(0, BigDecimal.valueOf(840).compareTo(medicalRiskCalculator.calculate(request,request.getPersonList().getFirst())));

    }

    @Test
    void calculateWorkCorrectlyIflimitDisablesAndAgeEnabled() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        medicalRiskCalculator.setLimitEnabled(false);
        medicalRiskCalculator.setAgeCoefEnabled(true);
        Mockito.when(dateTimeService.calculateAgeByBirthday(request.getPersonList().getFirst().getBirthday())).thenReturn(22);
        Mockito.when(ageCoefRepository.findCoefByAge(22)).thenReturn(new AgeCoef(3L, 18, 26, 1.2d));
        Mockito.when(countryRepository.findByTitle("Россия")).thenReturn(new Country(1L, "Россия", 100d));

        Mockito.when(dateTimeService.calculateDaysBetween
                (LocalDate.now().plusDays(1), LocalDate.now().plusDays(6))).thenReturn(BigDecimal.valueOf(6));
        assertEquals(0, BigDecimal.valueOf(720).compareTo(medicalRiskCalculator.calculate(request,request.getPersonList().getFirst())));

    }

    @Test
    void calculateWorkCorrectlyIflimitDisablesAndAgeDisabled() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        medicalRiskCalculator.setLimitEnabled(false);
        medicalRiskCalculator.setAgeCoefEnabled(false);
        Mockito.when(countryRepository.findByTitle("Россия")).thenReturn(new Country(1L, "Россия", 100d));
        Mockito.when(dateTimeService.calculateDaysBetween
                (LocalDate.now().plusDays(1), LocalDate.now().plusDays(6))).thenReturn(BigDecimal.valueOf(6));
        assertEquals(0, BigDecimal.valueOf(600).compareTo(medicalRiskCalculator.calculate(request,request.getPersonList().getFirst())));

    }
}