package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 class RequestDateFromBeforeDateToValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getAgreementDateFrom()==null
                ||request.getAgreementDateTo()==null
                ||request.getAgreementDateFrom().until(request.getAgreementDateTo()).getDays()<0)
                ?Optional.of(new ValidationError("dateFrom","dateFrom must be before than dateTo"))
                :Optional.empty();
    }
}
