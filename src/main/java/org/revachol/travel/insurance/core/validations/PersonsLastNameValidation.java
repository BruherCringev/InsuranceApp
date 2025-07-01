package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonsLastNameValidation implements TravelRequestValidation{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getPersonLastName() == null || request.getPersonLastName().isEmpty())
                ? Optional.of(new ValidationError("personLastName", "Must not be empty!"))
                : Optional.empty();
    }
}
