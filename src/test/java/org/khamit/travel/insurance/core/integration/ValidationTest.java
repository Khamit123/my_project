package org.khamit.travel.insurance.core.integration;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.core.validation.TravelCalculatePremiumRequestValidator;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidationTest {
    @Autowired
    private TravelCalculatePremiumRequestValidator validator;

    @Test
    public void allFieldsAreValidReturnNOError() {
        TravelCalculatePremiumRequestV2 requestV2 = UtillMethods.createRequestV2();
        List<ValidationError> errors = validator.validate(requestV2);
        assertTrue(errors.isEmpty());

    }

    @Test
    public void oneFieldsAreInvalidReturnOneError() {
        TravelCalculatePremiumRequestV2 requestV2 = UtillMethods.createRequestV2();
        requestV2.setAgreementDateTo(LocalDate.now());
        List<ValidationError> errors = validator.validate(requestV2);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "dateFrom must be before than dateTo");

    }

    @Test
    public void twoFieldsAreInvalidReturnTwoError() {
        TravelCalculatePremiumRequestV2 requestV2 = UtillMethods.createRequestV2();
        requestV2.setAgreementDateTo(LocalDate.now().minusDays(2));

        List<ValidationError> errors = validator.validate(requestV2);
        assertFalse(errors.isEmpty());
        assertEquals(2, errors.size());
        assertEquals(errors.get(0).getField(), "agreementDateFrom");
        assertEquals(errors.get(0).getMessage(), "dateFrom must be before than dateTo");

    }


}
