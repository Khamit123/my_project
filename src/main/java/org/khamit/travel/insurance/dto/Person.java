package org.khamit.travel.insurance.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor

public class Person {
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private BigDecimal medicalLimit;
    private PersonPremiumInfo premiumInfo;

    public Person(String personFirstName, String personLastName, LocalDate birthday,BigDecimal medicalLimit) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.birthday = birthday;
        this.medicalLimit = medicalLimit;
    }
}
