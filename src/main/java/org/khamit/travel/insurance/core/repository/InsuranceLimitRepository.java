package org.khamit.travel.insurance.core.repository;

import org.khamit.travel.insurance.core.domain.InsuranceLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceLimitRepository extends JpaRepository<InsuranceLimit, Long> {

    @Query("select a from InsuranceLimit a where a.limitMin<=?1 and a.limitMax>=?1")
    InsuranceLimit findCoefByLimit(Double limit);
}