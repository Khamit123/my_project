package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class RequestDateFromBeforeDateToValidationTest {

    private final RequestDateFromBeforeDateToValidation validation = new RequestDateFromBeforeDateToValidation();

    @Test
    void validateFieldDateFromNotBeforeDateToReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2125-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2125-01-11"));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("agreementDateFrom","dateFrom must be before than dateTo"),error.get());
    }
    @Test
    void validateFieldDateFromIsEqualDateToReturnNoErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2125-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2125-01-12"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldDateFromBeforeDateToReturnNoErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2125-01-12"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2125-01-13"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldDateFromIsNullDateToIsPresentReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.lenient().when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2125-01-13"));
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldDateFromIsPresentDateToIsNullReturnNoErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2125-01-13"));
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldDateFromIsNullDateToIsNullReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

}