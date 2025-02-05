package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class RequestDateFromBeforeDateToValidationTest {

    private final RequestDateFromBeforeDateToValidation validation = new RequestDateFromBeforeDateToValidation();

    @Test
    void validateFieldDateFromNotBeforeDateToReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2025-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2025-01-11"));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("dateFrom","dateFrom must be before than dateTo"),error.get());
    }
    @Test
    void validateFieldDateFromIsEqualDateToReturnNoErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2025-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2025-01-12"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldDateFromBeforeDateToReturnNoErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2025-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2025-01-13"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldDateFromIsNullDateToIsPresentReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.lenient().when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2025-01-13"));
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
    }

    @Test
    void validateFieldDateFromIsPresentDateToIsNullReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2025-01-13"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
    }

    @Test
    void validateFieldDateFromIsNullDateToIsNullReturnErrorTest() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
    }

}