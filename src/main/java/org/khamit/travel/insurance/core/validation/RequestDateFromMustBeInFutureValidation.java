package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
@Component
public class RequestDateFromMustBeInFutureValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getAgreementDateFrom()!=null
                && LocalDate.now().until(request.getAgreementDateFrom()).getDays()<0)
                ?Optional.of(new ValidationError("agreementDateFrom",
                "dateFrom must be in future"))
                :Optional.empty();
    }
}
