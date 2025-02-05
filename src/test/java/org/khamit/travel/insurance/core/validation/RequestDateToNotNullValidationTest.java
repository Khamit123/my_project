package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestDateToNotNullValidationTest {

    final RequestDateToNotNullValidation validation = new RequestDateToNotNullValidation();

    @Test
    void validateFieldDateToIsNullReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("dateTo","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldDateToIsNotNullNotReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2025-01-22"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

}