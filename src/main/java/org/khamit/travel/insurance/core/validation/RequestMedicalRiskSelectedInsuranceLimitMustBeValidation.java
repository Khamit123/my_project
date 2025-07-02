package org.khamit.travel.insurance.core.validation;

import lombok.Getter;
import lombok.Setter;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@Setter
@Getter
public class RequestMedicalRiskSelectedInsuranceLimitMustBeValidation implements RequestValidation{

    @Value("${medical.risk.limit.level.enabled}")
    private Boolean medicalEnabled;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequest request) {
        if(request.getSelectedRisks()==null || request.getSelectedRisks().isEmpty() || !medicalEnabled){
            return Optional.empty();
        }
        return (request.getSelectedRisks().contains("Медицинский риск") && request.getMedicalLimit()==null)?
                Optional.of(new ValidationError("insuranceLimit","Insurance Limit must be, if medical risk selected")):
                Optional.empty();
    }
}
