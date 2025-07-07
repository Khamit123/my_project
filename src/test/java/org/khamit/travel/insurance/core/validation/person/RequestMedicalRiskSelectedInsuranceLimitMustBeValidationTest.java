package org.khamit.travel.insurance.core.validation.person;

import org.junit.jupiter.api.Test;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.ValidationError;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class RequestMedicalRiskSelectedInsuranceLimitMustBeValidationTest {

    @Test
    void validateFieldMedicalEnabledIfMedicalRisksNotSelectedReturnNoError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(true);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setSelectedRisks(List.of("Отмена поездки"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldMedicalEnabledIfMedicalRisksIsNullReturnNoError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(true);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldMedicalEnabledIfMedicalRisksSelectedInsuranceLimitNotPresentReturnError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(true);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setSelectedRisks(List.of("Медицинский риск"));
        request.getPersonList().getFirst().setMedicalLimit(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertTrue(error.isPresent());
        assertEquals("personMedicalLimit", error.get().getField());
        assertEquals("Insurance Limit must be, if medical risk selected", error.get().getMessage());
    }

    @Test
    void validateFieldMedicalEnabledIfMedicalRisksSelectedInsuranceLimitIsPresentDontReturnError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(true);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    /////////////////////////////
    @Test
    void validateFieldMedicalDisabledIfMedicalRisksNotSelectedReturnNoError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(false);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setSelectedRisks(List.of("Отмена поездки"));
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
    @Test
    void validateFieldMedicalDisabledIfMedicalRisksIsNullReturnNoError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(false);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldMedicalDisabledIfMedicalRisksSelectedInsuranceLimitNotPresentReturnNoError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(false);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.getPersonList().getFirst().setMedicalLimit(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());

    }

    @Test
    void validateFieldMedicalDisabledIfMedicalRisksSelectedInsuranceLimitIsPresentDontReturnError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(false);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();

        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }
@Test
    void validateFieldPersonListIsNullDontReturnError() {
        RequestMedicalRiskSelectedInsuranceLimitMustBeValidation validation = new RequestMedicalRiskSelectedInsuranceLimitMustBeValidation();
        validation.setMedicalEnabled(false);
        TravelCalculatePremiumRequestV2 request = UtillMethods.createRequestV2();
        request.setPersonList(null);
        Optional<ValidationError> error = validation.validateField(request);
        assertFalse(error.isPresent());
    }

}