package org.khamit.travel.insurance.core.validation.person;

import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestLastNameNotEmptyAndNotNullValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        if(request.getPersonList()!=null) {
            for (Person person : request.getPersonList()) {
                if (person.getPersonLastName() == null || person.getPersonLastName().isEmpty()) {
                    return Optional.of(new ValidationError("personLastName", "Must not be empty!"));
                }
            }
        }
        return Optional.empty();
    }
}
