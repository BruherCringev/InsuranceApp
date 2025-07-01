package org.revachol.travel.insurance.core;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);
}
