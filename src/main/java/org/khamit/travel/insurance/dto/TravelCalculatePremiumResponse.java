package org.khamit.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponse extends CoreResponse {

    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private String country;
    private BigDecimal insuranceLimit;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(List<ValidationError> errors) {
        super(errors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TravelCalculatePremiumResponse response = (TravelCalculatePremiumResponse) o;
        return Objects.equals(personFirstName, response.personFirstName) && Objects.equals(personLastName, response.personLastName) && Objects.equals(agreementDateFrom, response.agreementDateFrom) && Objects.equals(agreementDateTo, response.agreementDateTo) && Objects.equals(birthday, response.birthday) && Objects.equals(country, response.country) && Objects.equals(insuranceLimit, response.insuranceLimit)
                && Objects.equals(agreementPrice, response.agreementPrice) && Objects.equals(response.getErrors(),super.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), personFirstName, personLastName, agreementDateFrom, agreementDateTo, birthday, country, insuranceLimit, agreementPrice);
    }

    @Override
    public String toString() {
        return "TravelCalculatePremiumResponse{" +
                "errors'" + super.getErrors() + '\'' +
                "personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", agreementDateFrom=" + agreementDateFrom +
                ", agreementDateTo=" + agreementDateTo +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", insuranceLimit=" + insuranceLimit +
                ", agreementPrice=" + agreementPrice +
                '}';
    }
}
