package org.khamit.travel.insurance.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private DateTimeServiceImpl dateTimeService;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void createRequest() {
        request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.parse("2025-01-22"));
        request.setAgreementDateTo(LocalDate.parse("2025-01-23"));
        request.setPersonFirstName("Khamit");
        request.setPersonLastName("Bil");
        Mockito.when(dateTimeService.calculateAgreementPrice(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
    }

    @Test
    public void calculatePremiumFirstNameCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void calculatePremiumLastNameCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void calculatePremiumDateToCorrect(){

        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());
    }

    @Test
    public void calculatePremiumDateFromCorrect(){
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals( request.getAgreementDateFrom(),response.getAgreementDateFrom());
    }

    @Test
    public void calculatePremiumAgreementPriceCorrect(){

        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals( BigDecimal.valueOf(2L),response.getAgreementPrice());
    }


}