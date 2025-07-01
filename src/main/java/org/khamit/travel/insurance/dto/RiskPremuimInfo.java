package org.khamit.travel.insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiskPremuimInfo {

    private String risk;
    private BigDecimal riskAmount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RiskPremuimInfo that = (RiskPremuimInfo) o;
        return Objects.equals(risk, that.risk) &&
                (Objects.equals(that.riskAmount, riskAmount) || 0 == that.riskAmount.compareTo(riskAmount));
    }

    @Override
    public int hashCode() {
        return Objects.hash(risk, riskAmount);
    }

    @Override
    public String toString() {
        return "RiskPremuimInfo{" +
                "risk='" + risk + '\'' +
                ", riskAmount=" + riskAmount +
                '}';
    }
}
