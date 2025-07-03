package org.khamit.travel.insurance.core.validation.person;

import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
 public class RequestFirstNameNotEmptyAndNotNullValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {

        for (Person person : request.getPersonList()) {
            if (person.getPersonFirstName() == null || person.getPersonFirstName().isEmpty()) {
                return Optional.of(new ValidationError("personFirstName", "Must not be empty!"));
            }
        }
        return Optional.empty();
    }
}
