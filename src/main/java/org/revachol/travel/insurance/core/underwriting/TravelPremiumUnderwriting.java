package org.revachol.travel.insurance.core.underwriting;

import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;

import java.math.BigDecimal;

public interface TravelPremiumUnderwriting {

    BigDecimal calculatePremium(TravelCalculatePremiumRequest request);

}
