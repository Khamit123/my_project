package org.khamit.travel.insurance.core.underwriting;

import org.khamit.travel.insurance.core.underwriting.calculator.RiskCalculator;
import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.v1.Person;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TravelPremiumUnderwriting {

    HashMap<String, RiskCalculator> calculators;
    public TravelPremiumUnderwriting(@Autowired List<RiskCalculator> riskCalculators) {
        calculators = new HashMap<>();
        riskCalculators.forEach(calc->calculators.put(calc.getTitle(),calc));
    }

    public PersonPremiumInfo calculateUnderwriting(TravelCalculatePremiumRequestV2 request, Person person){
        final BigDecimal[] allPremium = {BigDecimal.ZERO};
        List<RiskPremuimInfo> premiumForEachRisk =new ArrayList<>();
        request.getSelectedRisks().forEach(risk->{
            allPremium[0]=allPremium[0].add(calculators.get(risk).calculate(request,person));
            premiumForEachRisk.add(new RiskPremuimInfo(risk,calculators.get(risk).calculate(request,person)));
        });
        return new PersonPremiumInfo(allPremium[0],premiumForEachRisk);
    }
}
