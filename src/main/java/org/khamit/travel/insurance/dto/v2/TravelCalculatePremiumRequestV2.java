package org.khamit.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.khamit.travel.insurance.dto.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TravelCalculatePremiumRequestV2 {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    private List<String> selectedRisks;


    private String country;
    private List<Person> personList;
}
