package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class RequestMedicalRiskSelectedAgeMustBeTest {

    @Test
    void validateFieldIfMedicalRisksNotSelectedReturnNoError() {
        RequestValidation validation = new RequestMedicalRiskSelectedAgeMustBe();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Отмена поездки"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldIfMedicalRisksIsNullReturnNoError() {
        RequestValidation validation = new RequestMedicalRiskSelectedAgeMustBe();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldIfMedicalRisksSelectedAgeNotPresentReturnError() {
        RequestValidation validation = new RequestMedicalRiskSelectedAgeMustBe();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("birthday", error.get().getField());
        assertEquals("Birthday must be, if medical risk selected", error.get().getMessage());
    }

    @Test
    void validateFieldIfMedicalRisksSelectedAgeIsPresentDontReturnError() {
        RequestValidation validation = new RequestMedicalRiskSelectedAgeMustBe();
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        request.setBirthday(LocalDate.now());
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}