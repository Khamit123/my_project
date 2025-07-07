package org.khamit.travel.insurance.core.underwriting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.core.underwriting.calculator.MedicalRiskCalculator;
import org.khamit.travel.insurance.core.underwriting.calculator.TripCancellationCalculator;
import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TravelPremiumUnderwritingTest {

    @Mock
    MedicalRiskCalculator medicalRiskCalculator;
    @Mock
    TripCancellationCalculator tripCancellationCalculator;

    TravelPremiumUnderwriting travelPremiumUnderwriting;

    @Test
    void calculateUnderwritingWorkCorrectly() {

        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        Mockito.when(medicalRiskCalculator.getTitle()).thenReturn("Медицинский риск");
        Mockito.when(tripCancellationCalculator.getTitle()).thenReturn("Отмена поездки");
        Mockito.when(medicalRiskCalculator.calculate(request,request.getPersonList().getFirst())).thenReturn(BigDecimal.TWO);
        Mockito.when(tripCancellationCalculator.calculate(request,request.getPersonList().getFirst())).thenReturn(BigDecimal.TEN);
        travelPremiumUnderwriting = new TravelPremiumUnderwriting(List.of(medicalRiskCalculator, tripCancellationCalculator));
        PersonPremiumInfo answer= travelPremiumUnderwriting.calculateUnderwriting(request,request.getPersonList().getFirst());
        List<RiskPremuimInfo> premuimInfos=new ArrayList<>();
        premuimInfos.add(new RiskPremuimInfo("Медицинский риск",BigDecimal.TWO));
        premuimInfos.add(new RiskPremuimInfo("Отмена поездки",BigDecimal.TEN));
        assertNotNull(answer);
        assertNotNull(answer.getTotalPremium());
        assertNotNull(answer.getRiskPremuimInfo());
        assertEquals(0,answer.getTotalPremium().compareTo(BigDecimal.valueOf(12)));
        assertEquals(2,answer.getRiskPremuimInfo().size());
        assertEquals(premuimInfos,answer.getRiskPremuimInfo());
    }
}