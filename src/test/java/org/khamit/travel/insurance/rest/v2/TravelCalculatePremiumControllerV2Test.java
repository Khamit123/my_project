package org.khamit.travel.insurance.rest.v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.core.testUtill.UtillMethods;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumRequestV2;
import org.khamit.travel.insurance.dto.v2.TravelCalculatePremiumResponseV2;
import org.khamit.travel.insurance.dto.ValidationError;
import org.khamit.travel.insurance.rest.JsonToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {" medical.risk.limit.level.enabled=true",
        "medical.risk.ageCoef.enabled=true"})
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    TravelCalculatePremiumRequestV2 request;
    TravelCalculatePremiumResponseV2 expectedResponse;

    @BeforeEach
    public void setUp() {
        request = UtillMethods.createRequestV2();
        expectedResponse = UtillMethods.createResponseV2();
    }


    @Test
    public void firstNameNotProvided() throws Exception {
        request.getPersonList().getFirst().setPersonFirstName(null);
        expectedResponse = new TravelCalculatePremiumResponseV2(List.of(new ValidationError("personFirstName", "Must not be empty!")));
        executeAndCompare(request, expectedResponse);
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        request.getPersonList().getFirst().setPersonLastName(null);
        expectedResponse = new TravelCalculatePremiumResponseV2(List.of(new ValidationError("personLastName", "Must not be empty!")));
        executeAndCompare(request, expectedResponse);
    }


    @Test
    public void agreementDateFromNotProvided() throws Exception {
        request.setAgreementDateFrom(null);
        expectedResponse = new TravelCalculatePremiumResponseV2(List.of(new ValidationError("agreementDateFrom", "Must not be empty!")));
        executeAndCompare(request, expectedResponse);
    }
//
//    @Test
//    public void agreementDateToNotProvided() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_agreementDateTo_not_provided.json",
//                "rest/TravelCalculatePremium/Response_agreementDateTo_not_provided.json"
//        );
//    }
//
//    @Test
//    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_dateTo_lessThen_dateFrom.json",
//                "rest/TravelCalculatePremium/Response_dateTo_lessThen_dateFrom.json"
//        );
//    }
//
//    @Test
//    public void allFieldsNotProvided() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_allFields_not_provided.json",
//                "rest/TravelCalculatePremium/Response_allFields_not_provided.json"
//        );
//    }
//    @Test
//    public void dateToNotInFuture() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_agreementDateTo_not_provided.json",
//                "rest/TravelCalculatePremium/Response_agreementDateTo_not_provided.json"
//        );
//    }
//    @Test
//    public void dateFromNotInFuture() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_agreementDateFrom_not_provided.json",
//                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_provided.json"
//        );
//    }
//
    @Test
    public void successRequest() throws Exception {
        executeAndCompare(request, expectedResponse);
    }


    private void executeAndCompare(TravelCalculatePremiumRequestV2 request, TravelCalculatePremiumResponseV2 expectedResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String requestContent = mapper.writeValueAsString(request);
        MvcResult result = mockMvc.perform(post("/insurance/travel/v2/")
                        .content(requestContent)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        TravelCalculatePremiumResponseV2 actualResponse = mapper.readValue(responseBodyContent, TravelCalculatePremiumResponseV2.class);
        assertEquals(expectedResponse, actualResponse);
        //assertJson(e).where().keysInAnyOrder().arrayInAnyOrder().isEqualTo(responseBodyContent);
    }

}
