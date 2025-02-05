package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumRequestValidatorTest {

    @Mock private RequestValidation requestValidation;
    @Mock private RequestValidation requestValidation2;
    private  TravelCalculatePremiumRequestValidator validator;


    @Test
    void validateWithoutErrorsReturnNothing() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        validator =new TravelCalculatePremiumRequestValidator
                (List.of(requestValidation,requestValidation2));
        Mockito.when(requestValidation.validateField(request))
                .thenReturn(Optional.empty());
        Mockito.when(requestValidation2.validateField(request))
                .thenReturn(Optional.empty());
        List<ValidationError> errors =validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void validateWithErrorsReturnErrors() {
        TravelCalculatePremiumRequest request = Mockito.mock(TravelCalculatePremiumRequest.class);
        validator =new TravelCalculatePremiumRequestValidator
                (List.of(requestValidation,requestValidation2));
        Mockito.when(requestValidation.validateField(request))
                .thenReturn(Optional.of(new ValidationError("personFirstName","Must not be empty!")));
        Mockito.when(requestValidation2.validateField(request))
                .thenReturn(Optional.of(new ValidationError("personLastName","Must not be empty!")));
        List<ValidationError> errors =validator.validate(request);

        assertFalse(errors.isEmpty());
        assertEquals(2,errors.size());
    }

}