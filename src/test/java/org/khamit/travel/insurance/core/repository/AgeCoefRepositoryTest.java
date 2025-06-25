package org.khamit.travel.insurance.core.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.domain.AgeCoef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class AgeCoefRepositoryTest {

    @Autowired
    AgeCoefRepository ageCoefRepository;

    @Test
    void FindCoefByAgeWorkCorrect(){
       AgeCoef ageCoef= ageCoefRepository.findCoefByAge(20);
       assertNotNull(ageCoef);
       assertEquals(1.2,ageCoef.getCoef());
    }

}