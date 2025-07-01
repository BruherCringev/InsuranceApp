package org.revachol.travel.insurance.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestLogger {

    private final ObjectMapper objectMapper;

    void log(TravelCalculatePremiumRequest request) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(request);
            log.info("REQUEST: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert request to JSON", e);
        }
    }
}