package org.khamit.travel.insurance.core.integration.underwriting;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {" medical.risk.limit.level.enabled=false",
        "medical.risk.ageCoef.enabled=false"})
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class AgeCoefDisabledLimitDisabled {

    @Autowired
    TravelPremiumUnderwriting travelPremiumUnderwriting;
    @Test
    public void AgeCoefDisabledLimitDiableWorking(){
        TravelCalculatePremiumRequestV2 requestV2 = UtillMethods.createRequestV2();
        PersonPremiumInfo info=travelPremiumUnderwriting.calculateUnderwriting(requestV2,requestV2.getPersonList().getFirst());
        assertTrue(BigDecimal.valueOf(600).compareTo(info.getRiskPremuimInfo().getFirst().getRiskAmount())==0);
        assertTrue(BigDecimal.valueOf(10).compareTo(info.getRiskPremuimInfo().getLast().getRiskAmount())==0);
        assertTrue(BigDecimal.valueOf(610).compareTo(info.getTotalPremium())==0);

    }
}
