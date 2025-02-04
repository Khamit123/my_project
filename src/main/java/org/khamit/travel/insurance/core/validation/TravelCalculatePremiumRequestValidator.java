package org.khamit.travel.insurance.core.validation;


import lombok.RequiredArgsConstructor;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TravelCalculatePremiumRequestValidator {

    List<RequestValidation> errorsValidation;

    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = errorsValidation.stream()
                .map(requestValidation -> requestValidation.validateField(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return errors;
    }



}
