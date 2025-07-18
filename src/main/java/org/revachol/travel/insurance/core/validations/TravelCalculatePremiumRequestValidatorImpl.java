package org.revachol.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class TravelCalculatePremiumRequestValidatorImpl implements TravelCalculatePremiumRequestValidator {

    private final List<TravelRequestValidation> travelValidations;

    @Override
    public List<ValidationError> validate(TravelCalculatePremiumRequest request) {
        return travelValidations.stream()
                .map(validation -> validation.execute(request))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

}