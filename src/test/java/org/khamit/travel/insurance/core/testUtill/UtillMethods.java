package org.khamit.travel.insurance.core.testUtill;

import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class UtillMethods {
    public static TravelCalculatePremiumRequestV2 createRequest(){
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        request.setCountry("Россия");
        request.setMedicalLimit(BigDecimal.ONE);
        request.setBirthday(LocalDate.of(2003,6,15));
        request.setAgreementDateFrom(LocalDate.now().plusDays(1));
        request.setAgreementDateTo(LocalDate.now().plusDays(6));
        request.setPersonFirstName("Хамит");
        request.setPersonLastName("Билялетдинов");
        return request;
    }

    public static TravelCalculatePremiumResponseV2 createResponse(){
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(null);
        response.setCountry("Россия");
        response.setMedicalLimit(BigDecimal.ONE);
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
