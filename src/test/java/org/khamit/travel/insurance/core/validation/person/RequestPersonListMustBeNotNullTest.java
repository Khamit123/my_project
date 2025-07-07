package org.khamit.travel.insurance.core.validation.person;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestPersonListMustBeNotNullTest {

    RequestPersonListMustBeNotNullValidation validation = new RequestPersonListMustBeNotNullValidation();

    @Test
    void validateFieldPersonListIsNullReturnError() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setPersonList(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(error.get().getField(), "personList");
        assertEquals(error.get().getMessage(), "Need to add at least one person");

    }

    @Test
    void validateFieldPersonListNotNullReturnNoError() {
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());

    }
}