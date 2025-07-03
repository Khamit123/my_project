package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;

import java.util.Optional;

public interface RequestValidation {
    Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request);
}
