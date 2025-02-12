package org.khamit.travel.insurance.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private JsonToString jsonToString;

    @Test
    public void firstNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_firstName_not_provided.json",
                "rest/TravelCalculatePremium/Response_firstName_not_provided.json"
        );
    }

    @Test
    public void lastNameNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_lastName_not_provided.json",
                "rest/TravelCalculatePremium/Response_lastName_not_provided.json"
        );
    }

    @Test
    public void agreementDateFromNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void agreementDateToNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateTo_not_provided.json"
        );
    }

    @Test
    public void agreementDateToLessThenAgreementDateFrom() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremium/Response_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    public void allFieldsNotProvided() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_allFields_not_provided.json",
                "rest/TravelCalculatePremium/Response_allFields_not_provided.json"
        );
    }
    @Test
    public void dateToNotInFuture() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateTo_not_provided.json"
        );
    }
    @Test
    public void dateFromNotInFuture() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremium/Response_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    public void successRequest() throws Exception {
        executeAndCompare(
                "rest/TravelCalculatePremium/Request_success.json",
                "rest/TravelCalculatePremium/Response_success.json"
        );
    }


    private void executeAndCompare(String requestName, String expectedResponseName) throws Exception {
        String requestContent=jsonToString.read(requestName);
        String responseContent=jsonToString.read(expectedResponseName);

        MvcResult result =  mockMvc.perform(post("/insurance/travel/")
                .content(requestContent)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andReturn();

        String responseBodyContent = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        assertEquals(mapper.readTree(responseContent),mapper.readTree(responseBodyContent));
    }

}
