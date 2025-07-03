package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RequestMustHaveAtLeastOneRiskValidation implements RequestValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())?
                Optional.of(new ValidationError("selectedRisk","At least one risk must be selected")):
                Optional.empty();
    }
}
