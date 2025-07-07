package org.khamit.travel.insurance.core.validation.person;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestLastNameNotEmptyAndNotNullValidationTest {
    final RequestLastNameNotEmptyAndNotNullValidation validation = new RequestLastNameNotEmptyAndNotNullValidation();

    @Test
    void validateFieldLastNameIsNullReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setPersonLastName(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personLastName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldLastNameIsEmptyReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setPersonLastName("");
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personLastName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldLastNameIsNotNullAndNotEmptyNotReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
}