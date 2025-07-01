package org.revachol.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.core.ErrorCodeResolver;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class SelectedRisksValidation implements TravelRequestValidation{

    private final ErrorCodeResolver errorCodeResolver;

    @Override
    public Optional<ValidationError> execute(TravelCalculatePremiumRequest request) {
        return (request.getSelectedRisks() == null || request.getSelectedRisks().isEmpty())
                ? Optional.of(buildError("ERROR_CODE_4"))
                : Optional.empty();
    }
    private ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeResolver.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }
}
