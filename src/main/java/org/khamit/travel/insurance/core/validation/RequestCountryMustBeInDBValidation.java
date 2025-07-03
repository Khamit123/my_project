package org.khamit.travel.insurance.core.validation;

import org.khamit.travel.insurance.core.repository.CountryRepository;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestCountryMustBeInDBValidation implements RequestValidation {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public Optional<ValidationError> validateField(TravelCalculatePremiumRequestV2 request) {
        return (countryRepository.findByTitle(request.getCountry())==null && request.getCountry()!=null)?
                Optional.of(new ValidationError("country", "Calculating dont support this country")):
                Optional.empty();
    }
}
