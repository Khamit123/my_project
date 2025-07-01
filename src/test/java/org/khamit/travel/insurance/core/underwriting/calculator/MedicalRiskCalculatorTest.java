package org.khamit.travel.insurance.core.underwriting.calculator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.DateTimeService;
import org.khamit.travel.insurance.core.domain.AgeCoef;
import org.khamit.travel.insurance.core.domain.Country;
import org.khamit.travel.insurance.core.domain.InsuranceLimit;
import org.khamit.travel.insurance.core.repository.AgeCoefRepository;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.core.repository.InsuranceLimitRepository;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
    void calculateWorkCorrectly() {

        TravelCalculatePremiumRequest request =UtillMethods.createRequest();
        Mockito.when(dateTimeService.calculateAgeByBirthday(request.getBirthday())).thenReturn(22);
        Mockito.when(ageCoefRepository.findCoefByAge(22)).thenReturn(new AgeCoef(3L, 18, 26, 1.2d));
        Mockito.when(countryRepository.findByTitle("Россия")).thenReturn(new Country(1L, "Россия", 100d));
        Mockito.when(limitRepository.findCoefByLimit(BigDecimal.ONE.doubleValue())).
                thenReturn(new InsuranceLimit(1L, 0d, 1000d, 1d));
        Mockito.when(dateTimeService.calculateDaysBetween
                (LocalDate.now().plusDays(1), LocalDate.now().plusDays(6))).thenReturn(BigDecimal.valueOf(6));
        assertEquals(0, BigDecimal.valueOf(720).compareTo(medicalRiskCalculator.calculate(request)));

    }
}