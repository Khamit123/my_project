package org.khamit.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
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
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponse(errors);
        }


        return buildResponse(request,calculator.calculateUnderwriting(request));
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setErrors(errors);
        return response;
    }
    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, Pair<BigDecimal,List<RiskPremuimInfo>> decimalListPair) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(decimalListPair.getFirst());
        response.setCountry(request.getCountry());
        response.setBirthday(request.getBirthday());
        response.setMedicalLimit(request.getMedicalLimit());
        response.setRiskPremuimInfoList(decimalListPair.getSecond());
        return response;
    }


}
