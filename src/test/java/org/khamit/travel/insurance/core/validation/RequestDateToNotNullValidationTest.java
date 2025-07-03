package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestDateToNotNullValidationTest {

    final RequestDateToNotNullValidation validation = new RequestDateToNotNullValidation();

    @Test
    void validateFieldDateToIsNullReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("agreementDateTo","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldDateToIsNotNullNotReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getAgreementDateTo()).thenReturn(LocalDate.parse("2125-01-22"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

}