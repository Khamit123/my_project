package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestDateToMustBeInFutureValidationTest {
    private final RequestDateToMustBeInFutureValidation validation = new RequestDateToMustBeInFutureValidation();
    @Test
    void validateFieldDateToNotInFutureReturnErrorTest()
    {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2023-01-01"));
        Optional<ValidationError> error= validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("agreementDateTo",
                "dateTo must be in future"), error.get());
    }
    @Test
    void validateFieldDateToIsNullReturnNoErrorTest()
    {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError>  error= validation.validateField(request);
        assertFalse(error.isPresent());

    }
    @Test
    void validateFieldDateToNInFutureReturnNoErrorTest()
    {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2123-01-01"));
        Optional<ValidationError>  error= validation.validateField(request);
        assertFalse(error.isPresent());

    }

}