package org.khamit.travel.insurance.core.testUtill;

import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UtillMethods {
    public static TravelCalculatePremiumRequest createRequest(){
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        request.setCountry("Россия");
        request.setInsuranceLimit(BigDecimal.ONE);
        request.setBirthday(LocalDate.of(2003,6,15));
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        request.setAgreementDateTo(LocalDate.now().plusDays(6));
        request.setPersonFirstName("Хамит");
        request.setPersonLastName("Билялетдинов");
        return request;
    }

    public static TravelCalculatePremiumResponse createResponse(){
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setErrors(null);
        response.setCountry("Россия");
        response.setInsuranceLimit(BigDecimal.ONE);
        response.setBirthday(LocalDate.of(2003,6,15));
        response.setAgreementDateFrom(LocalDate.now().plusDays(1));
        response.setAgreementDateTo(LocalDate.now().plusDays(6));
        response.setPersonFirstName("Хамит");
        response.setPersonLastName("Билялетдинов");
        response.setAgreementPrice(BigDecimal.valueOf(730));
        response.setRiskPremuimInfoList(List.of(new RiskPremuimInfo("Медицинский риск",BigDecimal.valueOf(720)),
                new RiskPremuimInfo("Отмена поездки",BigDecimal.valueOf(10))));
        return response;
    }

}
