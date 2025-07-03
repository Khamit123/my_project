package org.khamit.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.khamit.travel.insurance.dto.CoreResponse;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;
import org.khamit.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV1 extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private String country;
    private BigDecimal medicalLimit;
    private BigDecimal agreementPrice;
    private List<RiskPremuimInfo> riskPremuimInfoList;

    public TravelCalculatePremiumResponseV1(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCalculatePremiumResponseV1 response = (TravelCalculatePremiumResponseV1) o;
        return Objects.equals(personFirstName, response.personFirstName) && Objects.equals(personLastName, response.personLastName) && Objects.equals(agreementDateFrom, response.agreementDateFrom) && Objects.equals(agreementDateTo, response.agreementDateTo) && Objects.equals(birthday, response.birthday) && Objects.equals(country, response.country) &&
                (Objects.equals(medicalLimit, response.medicalLimit) || 0 == medicalLimit.compareTo(response.medicalLimit)) && (Objects.equals(agreementPrice, response.agreementPrice) || 0 == agreementPrice.compareTo(response.agreementPrice))
                && Objects.equals(riskPremuimInfoList, response.riskPremuimInfoList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName, agreementDateFrom, agreementDateTo, birthday, country, medicalLimit, agreementPrice, riskPremuimInfoList);
    }

    @Override
    public String toString() {
        return "TravelCalculatePremiumResponse{" +
                "personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", agreementDateFrom=" + agreementDateFrom +
                ", agreementDateTo=" + agreementDateTo +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", insuranceLimit=" + medicalLimit +
                ", agreementPrice=" + agreementPrice +
                ", riskPremuimInfoList=" + riskPremuimInfoList +
                '}';
    }
}
