package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import java.util.Optional;

interface RequestValidation {
    Optional<ValidationError> validateField(TravelCalculatePremiumRequest request);
}
