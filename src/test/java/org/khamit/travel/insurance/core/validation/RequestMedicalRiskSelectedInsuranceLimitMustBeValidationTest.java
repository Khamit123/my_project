package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestMedicalRiskSelectedInsuranceLimitMustBeValidationTest {

    @Test
    void validateFieldIfMedicalRisksNotSelectedReturnNoError() {
        RequestValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Отмена поездки"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldIfMedicalRisksIsNullReturnNoError() {
        RequestValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldIfMedicalRisksSelectedInsuranceLimitNotPresentReturnError() {
        RequestValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("insuranceLimit", error.get().getField());
        assertEquals("Insurance Limit must be, if medical risk selected", error.get().getMessage());
    }

    @Test
    void validateFieldIfMedicalRisksSelectedInsuranceLimitIsPresentDontReturnError() {
        RequestValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        request.setInsuranceLimit(BigDecimal.ONE);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}