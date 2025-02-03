package org.khamit.travel.insurance.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.time.LocalDate;

class TravelCalculatePremiumServiceImplTest {

    private final TravelCalculatePremiumServiceImpl service = new TravelCalculatePremiumServiceImpl(new DateTimeServiceImpl());
    private TravelCalculatePremiumRequest request;

    @BeforeEach
    public void createRequest() {
        request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.parse("2025-01-22"));
        request.setAgreementDateTo(LocalDate.parse("2025-01-23"));
        request.setPersonFirstName("Khamit");
        request.setPersonLastName("Bil");
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
        Assertions.assertEquals(new BigDecimal("2"),response.getAgreementPrice());
    }




}