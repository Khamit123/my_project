package org.khamit.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelPremiumUnderwritingService underwritingService;
    private final TravelCalculatePremiumRequestValidator validator;

    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return buildResponse(errors);
        }


        return buildResponse(request,underwritingService.calculateUnderwriting(request.getAgreementDateFrom(),request.getAgreementDateTo()));
    }

    private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setErrors(errors);
        return response;
    }
    private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal agreementPrice) {
        TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
        response.setAgreementDateFrom(request.getAgreementDateFrom());
        response.setPersonFirstName(request.getPersonFirstName());
        response.setPersonLastName(request.getPersonLastName());
        response.setAgreementDateTo(request.getAgreementDateTo());
        response.setAgreementPrice(agreementPrice);
        return response;
    }


}
