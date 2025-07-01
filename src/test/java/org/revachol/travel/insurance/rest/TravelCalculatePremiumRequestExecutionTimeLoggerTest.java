package org.revachol.travel.insurance.rest;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TravelCalculatePremiumRequestExecutionTimeLoggerTest {
    @Test
    void shouldLogExecutionTime() {
        TravelCalculatePremiumRequestExecutionTimeLogger logger = new TravelCalculatePremiumRequestExecutionTimeLogger();

        LogCaptor logCaptor = LogCaptor.forClass(TravelCalculatePremiumRequestExecutionTimeLogger.class);

        String result = logger.logExecutionTime(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "done";
        });

        assertTrue(result.equals("done"));

        List<String> logs = logCaptor.getInfoLogs();
        assertTrue(logs.stream().anyMatch(log -> log.contains("Request processing time")));
    }
}

