package org.revachol.travel.insurance.core;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelPremiumUnderwriting {
    private final DateTimeService dateTimeService;

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeService.calculateDaysBetween(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()
        );
        return BigDecimal.valueOf(daysBetween);
    }

}
