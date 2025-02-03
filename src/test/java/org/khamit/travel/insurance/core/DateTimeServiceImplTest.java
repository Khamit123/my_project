package org.khamit.travel.insurance.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


class DateTimeServiceImplTest {
    DateTimeServiceImpl dateTimeService = new DateTimeServiceImpl();

    @Test
    public void shouldCalculateAgreementPriceBePositive(){
        BigDecimal price =  dateTimeService.calculateAgreementPrice(LocalDate.parse("2025-01-22"),LocalDate.parse("2025-01-23"));
        Assertions.assertEquals(new BigDecimal("2"),price);
    }
    @Test
    public void shouldCalculateAgreementPriceBeOne(){
        BigDecimal price =  dateTimeService.calculateAgreementPrice(LocalDate.parse("2025-01-22"),LocalDate.parse("2025-01-22"));
        Assertions.assertEquals(new BigDecimal("1"),price);
    }

}