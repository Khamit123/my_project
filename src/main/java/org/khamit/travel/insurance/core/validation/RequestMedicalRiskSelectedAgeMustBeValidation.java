package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RequestMedicalRiskSelectedAgeMustBeValidation implements RequestValidation{

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
            if(request.getSelectedRisks()==null || request.getSelectedRisks().isEmpty()){
                return Optional.empty();
            }
        return (request.getSelectedRisks().contains("Медицинский риск") && request.getBirthday()==null)?
                Optional.of(new ValidationError("birthday","Birthday must be, if medical risk selected")):
                Optional.empty();
    }
}
