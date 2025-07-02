package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;
@Component

public class RequestAgeMustBeInPastValidation implements  RequestValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        return (request.getBirthday()!=null
                && request.getBirthday().until(LocalDate.now()).getDays()<0)
                ?Optional.of(new ValidationError("birthday", "Birthday must be in past!"))
                :Optional.empty();
    }
    }

