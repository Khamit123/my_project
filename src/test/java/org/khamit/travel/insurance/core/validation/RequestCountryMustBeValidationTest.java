package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestCountryMustBeValidationTest {

    @Test
    void validateFieldICountryIsNullPresentReturnError() {
        RequestValidation validation = new RequestCountryMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("country", error.get().getField());
        assertEquals("Country must be", error.get().getMessage());
    }

    @Test
    void validateFieldICountryIsEmptyPresentReturnError() {
        RequestValidation validation = new RequestCountryMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of(""));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("country", error.get().getField());
        assertEquals("Country must be", error.get().getMessage());
    }

    @Test
    void validateFieldCountryIsPresentDontReturnError() {
        RequestValidation validation = new RequestCountryMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        request.setCountry("Россия");
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}