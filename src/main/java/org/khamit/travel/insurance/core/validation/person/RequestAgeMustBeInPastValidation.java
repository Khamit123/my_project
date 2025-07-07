package org.khamit.travel.insurance.core.validation.person;

import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
@Component

public class RequestAgeMustBeInPastValidation implements RequestValidation {
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        if(request.getPersonList()!=null) {
            for (Person person : request.getPersonList()) {
                if (person.getBirthday() != null && person.getBirthday().until(LocalDate.now()).getDays() < 0) {
                    return Optional.of(new ValidationError("birthday", "Birthday must be in past!"));
                }
            }
        }
        return Optional.empty();
    }
    }

