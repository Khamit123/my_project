package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 class RequestDateFromNotNullValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
            return (request.getAgreementDateFrom() == null)
                    ? Optional.of(new ValidationError("agreementDateFrom", "Must not be empty!"))
                    : Optional.empty();

    }
}
