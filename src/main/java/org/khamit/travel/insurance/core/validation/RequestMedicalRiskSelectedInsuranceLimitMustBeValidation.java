package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class RequestMedicalRiskSelectedInsuranceLimitMustBeValidation implements RequestValidation{
    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        if(request.getSelectedRisks()==null || request.getSelectedRisks().isEmpty()){
            return Optional.empty();
        }
        return (request.getSelectedRisks().contains("Медицинский риск") && request.getInsuranceLimit()==null)?
                Optional.of(new ValidationError("insuranceLimit","Insurance Limit must be, if medical risk selected")):
                Optional.empty();
    }
}
