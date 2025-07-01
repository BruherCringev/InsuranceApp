package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;

import java.util.Optional;

interface TravelRequestValidation {
    Optional<ValidationError> execute(TravelCalculatePremiumRequest request);
}
