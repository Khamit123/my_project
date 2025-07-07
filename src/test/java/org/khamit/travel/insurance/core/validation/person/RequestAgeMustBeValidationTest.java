package org.khamit.travel.insurance.core.validation.person;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class RequestAgeMustBeValidationTest {

    @Test
    void validateFieldBirthdayIsNullReturnError() {
        RequestAgeMustBeValidation validation = new RequestAgeMustBeValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setBirthday(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("birthday","Birthday must be not null!"),error.get());
    }

    @Test
    void validateFieldBirthdayIsNotNullNotReturnError() {
        RequestAgeMustBeValidation validation = new RequestAgeMustBeValidation();
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}