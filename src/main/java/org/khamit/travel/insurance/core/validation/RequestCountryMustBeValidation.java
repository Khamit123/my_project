package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RequestCountryMustBeValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {

        return (request.getCountry()==null || request.getCountry().isEmpty())?
                Optional.of(new ValidationError("country","Country must be")):
                Optional.empty();
    }
}
