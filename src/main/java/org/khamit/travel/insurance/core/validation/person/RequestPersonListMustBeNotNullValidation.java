package org.khamit.travel.insurance.core.validation.person;

import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;

import java.util.Optional;

public class RequestPersonListMustBeNotNullValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {

        return request.getPersonList()==null?
                Optional.of(new ValidationError("personList", "Need to add at least one person"))
                : Optional.empty();
    }
}
