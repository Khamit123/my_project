package org.khamit.travel.insurance.core.validation.person;

import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class RequestAgeMustBeValidation implements RequestValidation {

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        for(Person person:request.getPersonList()) {
            if (person.getBirthday() == null ) {
                return  Optional.of(new ValidationError("birthday","Birthday must be not null!"));
            }
        }
        return Optional.empty();

    }
}
