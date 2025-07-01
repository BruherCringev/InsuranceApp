package org.revachol.travel.insurance.core;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TravelPremiumUnderwritingTest {
    @Mock
    private DateTimeService dateTimeService;

    @InjectMocks
    private TravelPremiumUnderwriting premiumUnderwriting;

    @Test
    public void shouldReturnCorrectAgreementPrice() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        LocalDate from = LocalDate.of(2025, 3, 10);
        LocalDate to = LocalDate.of(2025, 3, 25);

        when(request.getAgreementDateFrom()).thenReturn(from);
        when(request.getAgreementDateTo()).thenReturn(to);
        when(dateTimeService.calculateDaysBetween(from, to)).thenReturn(15L);

        BigDecimal premium = premiumUnderwriting.calculatePremium(request);

        assertEquals(BigDecimal.valueOf(15L), premium);
    }
}
