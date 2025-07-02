package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class RequestAgeMustBeValidationTest {

    @Test
    void validateFieldBirthdayIsNullReturnError() {
        RequestAgeMustBeValidation validation = new RequestAgeMustBeValidation();
        TravelCalculatePremiumRequest request = UtillMethods.createRequest();
        request.setBirthday(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("birthday","Birthday must be not null!"),error.get());
    }

    @Test
    void validateFieldBirthdayIsNotNullNotReturnError() {
        RequestAgeMustBeValidation validation = new RequestAgeMustBeValidation();
        TravelCalculatePremiumRequest request = UtillMethods.createRequest();
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}