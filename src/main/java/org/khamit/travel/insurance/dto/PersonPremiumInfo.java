package org.khamit.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonPremiumInfo {
    private BigDecimal totalPremium;
    private List<RiskPremuimInfo> riskPremuimInfo;
}
