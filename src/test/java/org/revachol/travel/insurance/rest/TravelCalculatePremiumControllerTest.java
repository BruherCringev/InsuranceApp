package org.revachol.travel.insurance.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.revachol.travel.insurance.core.util.ErrorCodeResolver;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelCalculatePremiumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JsonFileReader jsonFileReader;

    @Autowired
    private ErrorCodeResolver errorCodeResolver;

    private void performAndAssert(String requestPath, String responsePath) throws Exception {
        String requestJson = jsonFileReader.readJsonFromFile(requestPath);
        String expectedResponse = jsonFileReader.readJsonFromFile(responsePath);

        String actualResponse = mockMvc.perform(post("/insurance/travel/")
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONAssert.assertEquals(expectedResponse, actualResponse, false);
    }

    @Test
    @DisplayName("Test case 1: firstName is not provided")
    public void firstNameNotProvided() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_firstName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_firstName_not_provided.json"
        );
    }

    @Test
    @DisplayName("Test case 2: lastName is not provided")
    public void lastNameNotProvided() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_lastName_not_provided.json",
                "rest/TravelCalculatePremiumResponse_lastName_not_provided.json"
        );
    }

    @Test
    @DisplayName("Test case 3: agreementDateFrom is not provided")
    public void agreementDateFromNotProvided() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_agreementDateFrom_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateFrom_not_provided.json"
        );
    }

    @Test
    @DisplayName("Test case 4: agreementDateTo is not provided")
    public void agreementDateToNotProvided() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_agreementDateTo_not_provided.json",
                "rest/TravelCalculatePremiumResponse_agreementDateTo_not_provided.json"
        );
    }

    @Test
    @DisplayName("Test case 5: all fields are not provided")
    public void allFieldsNotProvided() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_allFields_not_provided.json",
                "rest/TravelCalculatePremiumResponse_allFields_not_provided.json"
        );
    }

    @Test
    @DisplayName("Test case 6: agreementDateTo < agreementDateFrom")
    public void agreementDateToLessThanAgreementDateFrom() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_dateTo_lessThen_dateFrom.json",
                "rest/TravelCalculatePremiumResponse_dateTo_lessThen_dateFrom.json"
        );
    }

    @Test
    @DisplayName("Test case 7: success")
    public void success() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_success.json",
                "rest/TravelCalculatePremiumResponse_success.json"
        );
    }
    @Test
    @DisplayName("Test case 8: selectedRisksIsNull")
    public void selectedRisksIsNull() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_selectedRisks_null.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_null.json"
        );
    }

    @Test
    @DisplayName("Test case 9: selectedRisksIsEmpty")
    public void selectedRisksIsEmpty() throws Exception {
        performAndAssert(
                "rest/TravelCalculatePremiumRequest_selectedRisks_empty.json",
                "rest/TravelCalculatePremiumResponse_selectedRisks_empty.json"
        );
    }
}