package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.ValidationError;

import java.util.Optional;

interface TravelRequestValidation {
    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
