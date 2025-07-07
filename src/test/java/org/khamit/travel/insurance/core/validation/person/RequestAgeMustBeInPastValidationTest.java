package org.khamit.travel.insurance.core.validation.person;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestAgeMustBeInPastValidationTest {

    @Test
    void validateFieldAgeIsNullNotReturnError() {
        RequestAgeMustBeInPastValidation validator = new RequestAgeMustBeInPastValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setBirthday(null);
        assertTrue(validator.validateField(request).isEmpty());
    }

    @Test
    void validateFieldAgeInPastNotReturnError() {
        RequestAgeMustBeInPastValidation validator = new RequestAgeMustBeInPastValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        assertTrue(validator.validateField(request).isEmpty());
    }

    @Test
    void validateFieldAgeNotInPastReturnError() {
        RequestAgeMustBeInPastValidation validator = new RequestAgeMustBeInPastValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setBirthday(LocalDate.now().plusDays(1));
        Optional<ValidationError> error = validator.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("birthday", error.get().getField());
        assertEquals("Birthday must be in past!", error.get().getMessage());
    }

    @Test
    void validateFieldPersonListIsNullReturnNoError() {
        RequestAgeMustBeInPastValidation validator = new RequestAgeMustBeInPastValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setPersonList(null);
        Optional<ValidationError> error = validator.validateField(request);
        assertFalse(error.isPresent());

    }
    @Test
    void validateFieldPersonListIsEmptyReturnNoError() {
        RequestAgeMustBeInPastValidation validator = new RequestAgeMustBeInPastValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setPersonList(List.of());
        Optional<ValidationError> error = validator.validateField(request);
        assertFalse(error.isPresent());

    }
}