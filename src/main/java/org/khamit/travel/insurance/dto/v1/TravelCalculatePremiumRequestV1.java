package org.khamit.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TravelCalculatePremiumRequestV1 {
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;

    private List<String> selectedRisks;
    // Медицинский риск
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private String country;
    private BigDecimal medicalLimit;
    //Медицинский риск


    @Override
    public String toString() {
        return "TravelCalculatePremiumRequest{" +
                "personFirstName='" + personFirstName + '\'' +
                ", personLastName='" + personLastName + '\'' +
                ", agreementDateFrom=" + agreementDateFrom +
                ", agreementDateTo=" + agreementDateTo +
                ", selectedRisks=" + selectedRisks +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                ", insuranceLimit=" + medicalLimit +
                '}';
    }
}
