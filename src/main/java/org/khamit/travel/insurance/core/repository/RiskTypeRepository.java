package org.khamit.travel.insurance.core.repository;

import org.khamit.travel.insurance.core.domain.RiskType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiskTypeRepository extends JpaRepository<RiskType, Long> {

    List<RiskType> findByTitle(String tittle);

    List<String> findAllTitles();
}