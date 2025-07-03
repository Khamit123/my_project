package org.khamit.travel.insurance.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.khamit.travel.insurance.dto.CoreResponse;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.dto.v1.Person;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumResponseV2 extends CoreResponse {

    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateFrom;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate agreementDateTo;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private String country;
    private BigDecimal medicalLimit;
    private BigDecimal agreementPrice;
    List<Person> personList;

    public TravelCalculatePremiumResponseV2(List<ValidationError> errors) {
        super(errors);
    }
}
