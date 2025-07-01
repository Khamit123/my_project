package org.khamit.travel.insurance.core.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CountryRepositoryTest {
    @Autowired
    CountryRepository countryRepository;

    @Test
    void getCountrybyTitle() {
       Country country= countryRepository.findByTitle("Россия");
        assertNotNull(country);
        assertEquals("Россия",country.getTitle());

    }

}