package org.khamit.travel.insurance.core;

import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock private TravelPremiumUnderwritingService underwritingService;
    @Mock private TravelCalculatePremiumRequestValidator validator;
    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    public TravelCalculatePremiumRequest createRequest() {
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setAgreementDateFrom(LocalDate.parse("2025-01-22"));
        request.setAgreementDateTo(LocalDate.parse("2025-01-23"));
        request.setPersonFirstName("Khamit");
        request.setPersonLastName("Bil");
        return request;
    }

    @Test
    public void calculatePremiumFirstNameCorrect(){
        TravelCalculatePremiumRequest request = createRequest();
        Mockito.when(underwritingService.calculateUnderwriting(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void calculatePremiumLastNameCorrect(){
        TravelCalculatePremiumRequest request = createRequest();
        Mockito.when(underwritingService.calculateUnderwriting(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void calculatePremiumDateToCorrect(){
        TravelCalculatePremiumRequest request = createRequest();
        Mockito.when(underwritingService.calculateUnderwriting(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals(request.getAgreementDateTo(),response.getAgreementDateTo());
    }

    @Test
    public void calculatePremiumDateFromCorrect(){
        TravelCalculatePremiumRequest request = createRequest();
        Mockito.when(underwritingService.calculateUnderwriting(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals( request.getAgreementDateFrom(),response.getAgreementDateFrom());
    }

    @Test
    public void calculatePremiumAgreementPriceCorrect(){
        TravelCalculatePremiumRequest request = createRequest();
        Mockito.when(underwritingService.calculateUnderwriting(Mockito.any(), Mockito.any())).thenReturn(BigDecimal.valueOf(2L));
        TravelCalculatePremiumResponse response= service.calculatePremium(request);
        Assertions.assertEquals( BigDecimal.valueOf(2L),response.getAgreementPrice());
    }

    @Test
    public void calculatePremiumRequestWithErrorsReturnErrors(){
        TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new ValidationError("","")));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        Assertions.assertTrue(response.hasErrors());
    }

    @Test
    public void calculatePremiumRequestWithErrorsReturnCorrectNumberOfErrors(){
        TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new ValidationError("","")));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        Assertions.assertEquals(1,response.getErrors().size());
    }
    @Test
    public void calculatePremiumRequestWithErrorsReturnCorrectNameOfErrors(){
        TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new ValidationError("one","one")));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        Assertions.assertEquals(new ValidationError("one","one"),response.getErrors().getFirst());
    }

    @Test
    public void calculatePremiumRequestWithErrorsAllFieldIsNull() {
        TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new ValidationError("one","one")));
        TravelCalculatePremiumResponse response = service.calculatePremium(request);
        Assertions.assertNull(response.getPersonFirstName());
        Assertions.assertNull(response.getPersonLastName());
        Assertions.assertNull(response.getAgreementDateFrom());
        Assertions.assertNull(response.getAgreementDateTo());
        Assertions.assertNull(response.getAgreementPrice());
    }
    @Test
    public void calculatePremiumRequestWithErrorsDontInvokeDateTimeService() {
        TravelCalculatePremiumRequest request =new TravelCalculatePremiumRequest();
        Mockito.when(validator.validate(request)).thenReturn(List.of(new ValidationError("one","one")));
        service.calculatePremium(request);
        Mockito.verifyNoInteractions(underwritingService);
    }











}