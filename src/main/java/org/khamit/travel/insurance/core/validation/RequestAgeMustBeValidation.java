package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RequestAgeMustBeValidation implements RequestValidation{

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getBirthday()==null)?
                Optional.of(new ValidationError("birthday","Birthday must be not null!")):
                Optional.empty();
    }
}
