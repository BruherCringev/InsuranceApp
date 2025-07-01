package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class DateToLessThenFromValidation implements TravelRequestValidation{

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        LocalDate dateFrom = request.getAgreementDateFrom();
        LocalDate dateTo = request.getAgreementDateTo();
        return (dateFrom != null && dateTo != null && (dateTo.isBefore(dateFrom) || dateTo.equals(dateFrom)))
                ? Optional.of(new ValidationError("agreementDateTo", "Must be less then agreementDateTo!"))
                : Optional.empty();
    }
}
