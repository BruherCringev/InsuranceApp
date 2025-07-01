package org.revachol.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TravelCalculatePremiumResponseLoggerTest {

    private ObjectMapper objectMapper;
    private ObjectWriter objectWriter;
    private TravelCalculatePremiumResponseLogger logger;

    @BeforeEach
    void setup() {
        objectMapper = mock(ObjectMapper.class);
        objectWriter = mock(ObjectWriter.class);
        logger = new TravelCalculatePremiumResponseLogger(objectMapper);

        when(objectMapper.writerWithDefaultPrettyPrinter()).thenReturn(objectWriter);
    }

    @Test
    void shouldLogResponseAsJson() throws JsonProcessingException {

        TravelCalculatePremiumResponse response = mock(TravelCalculatePremiumResponse.class);
        String jsonString = "{ \"some\": \"json\" }";

        when(objectWriter.writeValueAsString(response)).thenReturn(jsonString);

        LogCaptor logCaptor = LogCaptor.forClass(TravelCalculatePremiumResponseLogger.class);

        logger.log(response);

        assertTrue(logCaptor.getInfoLogs().stream()
                .anyMatch(log -> log.contains("RESPONSE:") && log.contains(jsonString)));

        logCaptor.close();
    }
}
