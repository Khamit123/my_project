package org.khamit.travel.insurance.rest;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TravelCalculatePremiumRequest {
    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;


}
