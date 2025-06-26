package org.khamit.travel.insurance.core.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.domain.RiskType;
import org.khamit.travel.insurance.core.repository.RiskTypeRepository;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.ValidationError;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RequestRiskMustBeInDBValidationTest {

    @Mock
    RiskTypeRepository riskTypeRepository;
    @InjectMocks
    RequestRiskMustBeInDBValidation valid;


    @Test
    void validateFieldNoRiskReturnNotError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of());
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldRiskIsNullReturnNotError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(null);
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldOneRiskInDBReturnNotError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск"));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldRiskTwoInDBReturnNotError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));

        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        Optional<ValidationError> error = valid.validateField(request);
        assertFalse(error.isPresent());
    }

    @Test
    void validateFieldRiskOneInDBOneNotInDBReturnError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездкdsddsи"));
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("selectedRisks","Следующие риски не существуют: Отмена поездкdsddsи,"),error.get());
    }

    @Test
    void validateFieldRiskTwoNotInDBReturnError() {
        Mockito.when(riskTypeRepository.findAll()).thenReturn(List.of(new RiskType("Медицинский риск"),new RiskType("Отмена поездки")));
        TravelCalculatePremiumRequest request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск11","Отмена поездкdsddsи"));
        Optional<ValidationError> error = valid.validateField(request);
        assertTrue(error.isPresent());
        assertEquals(new ValidationError("selectedRisks","Следующие риски не существуют: Медицинский риск11, Отмена поездкdsddsи,"),error.get());
    }
}