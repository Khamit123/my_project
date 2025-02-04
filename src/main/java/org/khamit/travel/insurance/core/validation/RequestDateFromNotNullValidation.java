package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 class RequestDateFromNotNullValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
            return (request.getAgreementDateFrom() == null)
                    ? Optional.of(new ValidationError("dateFrom", "Must not be empty!"))
                    : Optional.empty();

    }
}
