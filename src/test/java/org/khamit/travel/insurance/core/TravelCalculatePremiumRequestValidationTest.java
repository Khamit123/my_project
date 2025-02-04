package org.khamit.travel.insurance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidationTest {
   @Mock TravelCalculatePremiumRequest request;
   private final TravelCalculatePremiumRequestValidator validator =new TravelCalculatePremiumRequestValidator();

    @Test
    void validateFirstNameIsCorrectReturnNoError() {
        Mockito.when(request.getPersonFirstName()).thenReturn("Khamit");
        List<ValidationError> errors =validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    void validateFirstNameIsNullReturnCorrectError() {
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors =validator.validate(request);
        assertTrue(errors.contains(new ValidationError("personFirstName", "Must not be empty!")));
    }
    @Test
    void validateFirstNameIsNull() {
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        List<ValidationError> errors =validator.validate(request);
        assertTrue(errors.contains(new ValidationError("personFirstName", "Must not be empty!")));
    }
}