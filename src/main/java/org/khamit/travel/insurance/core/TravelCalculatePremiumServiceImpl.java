package org.khamit.travel.insurance.core;

import org.khamit.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.rest.TravelCalculatePremiumResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final DateTimeService dateTimeService;

    @Autowired
    public TravelCalculatePremiumServiceImpl(DateTimeServiceImpl dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(dateTimeService.calculateAgreementPrice(request.getAgreementDateFrom(),request.getAgreementDateTo()));
        return response;
    }


}
