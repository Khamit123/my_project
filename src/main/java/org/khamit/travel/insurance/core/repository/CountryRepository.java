package org.khamit.travel.insurance.core.repository;

import org.khamit.travel.insurance.core.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findByTitle(String title);
}