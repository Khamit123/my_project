package org.khamit.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPremiumUnderwriting calculator;
    private final TravelCalculatePremiumRequestValidator validator;

    @Override
    public TravelCalculatePremiumResponseV2 calculatePremium(TravelCalculatePremiumRequestV2 request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponse(errors);
        }
        final BigDecimal[] allPremium = {BigDecimal.ZERO};
        request.getPersonList().forEach(person -> {
            person.setPremiumInfo(calculator.calculateUnderwriting(request,person));
            allPremium[0] = allPremium[0].add(person.getPremiumInfo().getTotalPremium());
        });

        return buildResponse(request,allPremium[0]);
    }

    private TravelCalculatePremiumResponseV2 buildResponse(List<ValidationError> errors) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setErrors(errors);
        return response;
    }
    private TravelCalculatePremiumResponseV2 buildResponse(TravelCalculatePremiumRequestV2 request,BigDecimal premium) {
        TravelCalculatePremiumResponseV2 response = new TravelCalculatePremiumResponseV2();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonList(request.getPersonList());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(premium);
        response.setCountry(request.getCountry());
        response.setMedicalLimit(request.getMedicalLimit());
        return response;
    }


}
