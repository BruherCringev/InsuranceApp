package org.revachol.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumRequestLoggerTest {

    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;
    private TravelCalculatePremiumRequestLogger logger;
    private TravelCalculatePremiumResponseLogger logger2;

    @BeforeEach
    void setup() {
        objectMapper = mock(ObjectMapper.class);
        objectWriter = mock(ObjectWriter.class);
        logger = new TravelCalculatePremiumRequestLogger(objectMapper);
        logger2 = new TravelCalculatePremiumResponseLogger(objectMapper);

        when(objectMapper.writerWithDefaultPrettyPrinter()).thenReturn(objectWriter);
    }

    @Test
    void shouldLogResponseAsJson() throws JsonProcessingException {

        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);
        String jsonString = "{ \"some\": \"json\" }";

        when(objectWriter.writeValueAsString(response)).thenReturn(jsonString);

        LogCaptor logCaptor = LogCaptor.forClass(TravelCalculatePremiumResponseLogger.class);

        logger2.log(response);

        assertTrue(logCaptor.getInfoLogs().stream()
                .anyMatch(log -> log.contains("RESPONSE:") && log.contains(jsonString)));

        logCaptor.close();
    }

    @Test
    void shouldLogRequestAsJson() throws JsonProcessingException {

        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        String jsonString = "{ \"request\": \"data\" }";

        when(objectWriter.writeValueAsString(request)).thenReturn(jsonString);

        LogCaptor logCaptor = LogCaptor.forClass(TravelCalculatePremiumRequestLogger.class);


        logger.log(request);



        assertTrue(logCaptor.getInfoLogs().stream()
                .anyMatch(log -> log.contains("REQUEST:") && log.contains(jsonString)));

        logCaptor.close();
    }
}