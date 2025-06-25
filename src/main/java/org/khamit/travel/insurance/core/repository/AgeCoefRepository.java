package org.khamit.travel.insurance.core.repository;

import org.khamit.travel.insurance.core.domain.AgeCoef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgeCoefRepository extends JpaRepository<AgeCoef, Long> {

  @Query("select a from AgeCoef a where a.ageFrom<=?1 and a.ageTo>=?1")
   AgeCoef findCoefByAge(int age);


}