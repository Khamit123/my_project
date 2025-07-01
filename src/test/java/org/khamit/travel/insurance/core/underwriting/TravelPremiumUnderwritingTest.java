package org.khamit.travel.insurance.core.underwriting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.core.underwriting.calculator.MedicalRiskCalculator;
import org.khamit.travel.insurance.core.underwriting.calculator.TripCancellationCalculator;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.mockito.InjectMocks;
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

        TravelCalculatePremiumRequest request = UtillMethods.createRequest();
        Mockito.when(medicalRiskCalculator.getTitle()).thenReturn("Медицинский риск");
        Mockito.when(tripCancellationCalculator.getTitle()).thenReturn("Отмена поездки");
        Mockito.when(medicalRiskCalculator.calculate(request)).thenReturn(BigDecimal.TWO);
        Mockito.when(tripCancellationCalculator.calculate(request)).thenReturn(BigDecimal.TEN);
        travelPremiumUnderwriting = new TravelPremiumUnderwriting(List.of(medicalRiskCalculator, tripCancellationCalculator));
        Pair<BigDecimal, List<RiskPremuimInfo>> answer= travelPremiumUnderwriting.calculateUnderwriting(request);
        List<RiskPremuimInfo> premuimInfos=new ArrayList<>();
        premuimInfos.add(new RiskPremuimInfo("Медицинский риск",BigDecimal.TWO));
        premuimInfos.add(new RiskPremuimInfo("Отмена поездки",BigDecimal.TEN));
        assertNotNull(answer);
        assertNotNull(answer.getFirst());
        assertNotNull(answer.getSecond());
        assertEquals(0,answer.getFirst().compareTo(BigDecimal.valueOf(12)));
        assertEquals(2,answer.getSecond().size());
        assertEquals(premuimInfos,answer.getSecond());
    }
}