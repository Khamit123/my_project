package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestDateFromMustBeInFutureValidationTest {
    private final RequestDateFromMustBeInFutureValidation validation = new RequestDateFromMustBeInFutureValidation();

    @Test
    void validateFieldDateFromNotInFutureReturnErrorTest()
    {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2023-01-01"));
        Optional<ValidationError>  error= validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("agreementDateFrom",
                "dateFrom must be in future"), error.get());
    }
    @Test
    void validateFieldDateFromIsNullReturnNoErrorTest()
    {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError>  error= validation.validateField(request);
        assertFalse(error.isPresent());

    }
    @Test
    void validateFieldDateFromNInFutureReturnNoErrorTest()
    {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2123-01-01"));
        Optional<ValidationError>  error= validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("agreementDateFrom",
                "dateFrom must be in future"), error.get());
    }
}