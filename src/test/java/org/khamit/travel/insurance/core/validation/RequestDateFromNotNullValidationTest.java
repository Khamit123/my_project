package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestDateFromNotNullValidationTest {

    final RequestDateFromNotNullValidation validation = new RequestDateFromNotNullValidation();

    @Test
    void validateFieldDateFromIsNullReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("agreementDateFrom","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldDateFromIsNotNullNotReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateFrom()).thenReturn(LocalDate.parse("2125-01-22"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }



}