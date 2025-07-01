package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;

import java.util.List;

public interface TravelCalculatePremiumRequestValidator {
    List<ValidationError> validate(TravelCalculatePremiumRequest request);
}


