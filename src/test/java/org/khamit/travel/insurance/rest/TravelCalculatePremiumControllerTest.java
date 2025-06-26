package org.khamit.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.khamit.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.khamit.travel.insurance.dto.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.org.webcompere.modelassert.json.JsonAssertions.assertJson;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonToString jsonToString;
    TravelCalculatePremiumRequest request;
    TravelCalculatePremiumResponse expectedResponse;

    @BeforeEach
    public void setUp() {
        request = new TravelCalculatePremiumRequest();
        request.setSelectedRisks(List.of("Медицинский риск","Отмена поездки"));
        request.setCountry("Россия");
        request.setInsuranceLimit(BigDecimal.ONE);
        request.setBirthday(LocalDate.of(2003,6,15));
        request.setAgreementDateFrom(LocalDate.of(2026,6,15));
        request.setAgreementDateTo(LocalDate.of(2026,6,20));
        request.setPersonFirstName("Хамит");
        request.setPersonLastName("Билялетдинов");

        expectedResponse = new TravelCalculatePremiumResponse();
        expectedResponse.setErrors(null);
        expectedResponse.setCountry("Россия");
        expectedResponse.setInsuranceLimit(BigDecimal.ONE);
        expectedResponse.setBirthday(LocalDate.of(2003,6,15));
        expectedResponse.setAgreementDateFrom(LocalDate.now().plusDays(1));
        expectedResponse.setAgreementDateTo(LocalDate.now().plusDays(6));
        expectedResponse.setPersonFirstName("Хамит");
        expectedResponse.setPersonLastName("Билялетдинов");
        expectedResponse.setAgreementPrice(BigDecimal.valueOf(6));
    }



    @Test
    public void firstNameNotProvided() throws Exception {
        request.setPersonFirstName(null);
        expectedResponse=new TravelCalculatePremiumResponse(List.of(new ValidationError("personFirstName","Must not be empty!")));
        executeAndCompare(request,expectedResponse);
    }

//    @Test
//    public void lastNameNotProvided() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_lastName_not_provided.json",
//                "rest/TravelCalculatePremium/Response_lastName_not_provided.json"
//        );
//    }
//
//    @Test
//    public void agreementDateFromNotProvided() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_agreementDateFrom_not_provided.json",
//                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_provided.json"
//        );
//    }
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
//    @Test
//    public void successRequest() throws Exception {
//        executeAndCompare(
//                "rest/TravelCalculatePremium/Request_success.json",
//                "rest/TravelCalculatePremium/Response_success.json"
//        );
//    }


    private void executeAndCompare(TravelCalculatePremiumRequest request, TravelCalculatePremiumResponse expectedResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String requestContent = mapper.writeValueAsString(request);
        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                .content(requestContent)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        TravelCalculatePremiumResponse actualResponse = mapper.readValue(responseBodyContent, TravelCalculatePremiumResponse.class);
        assertEquals(expectedResponse,actualResponse);
        //assertJson(e).where().keysInAnyOrder().arrayInAnyOrder().isEqualTo(responseBodyContent);
    }

}
