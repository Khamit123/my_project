package org.khamit.travel.insurance.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.khamit.travel.insurance.dto.PersonPremiumInfo;
import org.khamit.travel.insurance.dto.RiskPremuimInfo;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class Person {
    private String personFirstName;
    private String personLastName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate birthday;
    private PersonPremiumInfo premiumInfo;

    public Person(String personFirstName, String personLastName, LocalDate birthday) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.birthday = birthday;
    }
}
