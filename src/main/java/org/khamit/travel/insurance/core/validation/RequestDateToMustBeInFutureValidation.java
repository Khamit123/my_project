package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
@Component
public class RequestDateToMustBeInFutureValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getAgreementDateTo()!=null
                && LocalDate.now().until(request.getAgreementDateTo()).getDays()<0)
                ?Optional.of(new ValidationError("agreementDateTo",
                "dateTo must be in future"))
                :Optional.empty();
    }
}
