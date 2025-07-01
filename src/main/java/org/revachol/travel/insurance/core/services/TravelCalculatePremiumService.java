package org.revachol.travel.insurance.core.services;

import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumResponse;

public interface TravelCalculatePremiumService {

    TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request);
}
