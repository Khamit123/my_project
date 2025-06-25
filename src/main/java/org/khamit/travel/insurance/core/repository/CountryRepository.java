package org.khamit.travel.insurance.core.repository;

import org.khamit.travel.insurance.core.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}