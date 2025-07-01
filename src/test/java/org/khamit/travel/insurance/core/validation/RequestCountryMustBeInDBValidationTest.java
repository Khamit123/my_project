package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.domain.Country;
import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.core.repository.RiskTypeRepository;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
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
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldCountryInDBReturnNotError() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Россия");
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(new Country(null,"Россия",100d));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldCountryNotInDBReturnError() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setCountry("Россия");
        Mockito.when(countryRepository.findByTitle(request.getCountry())).
                thenReturn(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("country", error.get().getField());
        assertEquals("Calculating dont support this country", error.get().getMessage());
    }



}