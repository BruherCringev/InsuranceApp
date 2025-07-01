package org.revachol.travel.insurance.rest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumResponseLogger {

    private final ObjectMapper objectMapper;

    void log(TravelCalculatePremiumResponse response) {
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
            log.info("RESPONSE: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert response to JSON", e);
        }
    }
}