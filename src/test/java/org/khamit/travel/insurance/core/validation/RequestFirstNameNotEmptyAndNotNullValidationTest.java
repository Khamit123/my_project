package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.validation.person.RequestFirstNameNotEmptyAndNotNullValidation;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RequestFirstNameNotEmptyAndNotNullValidationTest {

    final RequestFirstNameNotEmptyAndNotNullValidation validation = new RequestFirstNameNotEmptyAndNotNullValidation();

    @Test
    void validateFieldFirstNameIsNullReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personFirstName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldFirstNameIsEmptyReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals( new ValidationError("personFirstName","Must not be empty!"),error.get());
    }

    @Test
    void validateFieldFirstNameIsNotNullAndNotEmptyNotReturnErrorTest() {
        TravelCalculatePremiumRequestV2 request = Mockito.mock(TravelCalculatePremiumRequestV2.class);
        Mockito.when(request.getPersonFirstName()).thenReturn("Khamit");
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }



}