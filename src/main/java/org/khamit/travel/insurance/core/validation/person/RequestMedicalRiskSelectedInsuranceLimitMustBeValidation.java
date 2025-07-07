package org.khamit.travel.insurance.core.validation.person;

import lombok.Getter;
import lombok.Setter;
import org.khamit.travel.insurance.core.validation.RequestValidation;
import org.khamit.travel.insurance.dto.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Setter
@Getter
public class RequestMedicalRiskSelectedInsuranceLimitMustBeValidation implements RequestValidation {

    @Value("${medical.risk.limit.level.enabled}")
    private Boolean medicalEnabled;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        if (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty() || !medicalEnabled || request.getPersonList()==null) {
            return Optional.empty();
        }
        for (Person person : request.getPersonList()) {
            if (request.getSelectedRisks().contains("Медицинский риск") && person.getMedicalLimit() == null) {
                return Optional.of(new ValidationError("personMedicalLimit", "Insurance Limit must be, if medical risk selected"));
            }
        }
        return Optional.empty();
    }
}
