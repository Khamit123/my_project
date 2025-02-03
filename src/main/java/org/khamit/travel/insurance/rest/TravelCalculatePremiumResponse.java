package org.khamit.travel.insurance.rest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class TravelCalculatePremiumResponse {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;
    private LocalDate agreementDateTo;
    private BigDecimal agreementPrice;

    public TravelCalculatePremiumResponse(String personFirstName, String personLastName, LocalDate agreementDateFrom, LocalDate agreementDateTo) {
        this.personFirstName = personFirstName;
        this.personLastName = personLastName;
        this.agreementDateFrom = agreementDateFrom;
        this.agreementDateTo = agreementDateTo;
    }


    public TravelCalculatePremiumResponse() {
    }

    public BigDecimal getAgreementPrice() {
        return agreementPrice;
    }

    public void setAgreementPrice(BigDecimal agreementPrice) {
        this.agreementPrice = agreementPrice;
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
        TravelCalculatePremiumResponse that = (TravelCalculatePremiumResponse) o;
        return Objects.equals(personFirstName, that.personFirstName) && Objects.equals(personLastName, that.personLastName) && Objects.equals(agreementDateFrom, that.agreementDateFrom) && Objects.equals(agreementDateTo, that.agreementDateTo) && Objects.equals(agreementPrice, that.agreementPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personFirstName, personLastName, agreementDateFrom, agreementDateTo, agreementPrice);
    }
}
