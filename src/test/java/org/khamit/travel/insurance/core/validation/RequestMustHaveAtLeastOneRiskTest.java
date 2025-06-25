package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestMustHaveAtLeastOneRiskTest {

    @Test
    void validateFieldNoRisksReturnError() {
        RequestMustHaveAtLeastOneRisk valid = new RequestMustHaveAtLeastOneRisk();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of());
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getMessage(), "At least one risk must be selected");
    }
    @Test
    void validateFieldRisksIsNullReturnError() {
        RequestMustHaveAtLeastOneRisk valid = new RequestMustHaveAtLeastOneRisk();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getMessage(), "At least one risk must be selected");
    }

    @Test
    void validateFieldOneRisksReturnError() {
        RequestMustHaveAtLeastOneRisk valid = new RequestMustHaveAtLeastOneRisk();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Risk 1"));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldMoreThanOneRisksReturnError() {
        RequestMustHaveAtLeastOneRisk valid = new RequestMustHaveAtLeastOneRisk();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Risk 1", "Risk 2"));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }
}