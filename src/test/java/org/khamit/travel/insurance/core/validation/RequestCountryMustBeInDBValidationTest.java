package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.domain.Country;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RequestCountryMustBeInDBValidationTest {

    @Mock
    CountryRepository countryRepository;
    @InjectMocks
    RequestCountryMustBeInDBValidation valid;


    @Test
    void validateFieldNoCountryReturnNotError() {
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldCountryInDBReturnNotError() {
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        request.setCountry("Россия");
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(new Country(null,"Россия",100d));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldCountryNotInDBReturnError() {
        TravelCalculatePremiumRequestV2 request = new TravelCalculatePremiumRequestV2();
        request.setCountry("Россия");
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("country", error.get().getField());
        assertEquals("Calculating dont support this country", error.get().getMessage());
    }



}