package org.revachol.travel.insurance.core.underwriting;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.core.util.DateTimeService;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class TravelPremiumUnderwritingImpl implements TravelPremiumUnderwriting{

    private final DateTimeService dateTimeService;

    public BigDecimal calculatePremium(TravelCalculatePremiumRequest request) {
        var daysBetween = dateTimeService.calculateDaysBetween(
                request.getAgreementDateFrom(),
                request.getAgreementDateTo()
        );
        return BigDecimal.valueOf(daysBetween);
    }

}

