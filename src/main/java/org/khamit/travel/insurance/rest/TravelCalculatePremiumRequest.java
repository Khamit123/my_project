package org.khamit.travel.insurance.rest;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;



    public TravelCalculatePremiumRequest() {
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public TravelCalculatePremiumRequest(String personFirstName, String personLastName, LocalDate agreementDateFrom, LocalDate agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
    }

    public LocalDate getAgreementDateFrom() {
        return agreementDateFrom;
    }

    public void setAgreementDateFrom(LocalDate agreementDateFrom) {
        this.agreementDateFrom = agreementDateFrom;
    }

    public LocalDate getAgreementDateTo() {
        return agreementDateTo;
    }

    public void setAgreementDateTo(LocalDate agreementDateTo) {
        this.agreementDateTo = agreementDateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravelCalculatePremiumRequest that = (TravelCalculatePremiumRequest) o;
        return Objects.equals(personFirstName, that.personFirstName) && Objects.equals(personLastName, that.personLastName) && Objects.equals(agreementDateFrom, that.agreementDateFrom) && Objects.equals(agreementDateTo, that.agreementDateTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName, agreementDateFrom, agreementDateTo);
    }
}
