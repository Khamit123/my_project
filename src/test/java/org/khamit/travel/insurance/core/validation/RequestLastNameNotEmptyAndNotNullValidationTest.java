package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestLastNameNotEmptyAndNotNullValidationTest {
    final RequestLastNameNotEmptyAndNotNullValidation validation = new RequestLastNameNotEmptyAndNotNullValidation();

    @Test
    void validateFieldLastNameIsNullReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personLastName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldLastNameIsEmptyReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn("");
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personLastName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldLastNameIsNotNullAndNotEmptyNotReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getPersonLastName()).thenReturn("Khamit");
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}