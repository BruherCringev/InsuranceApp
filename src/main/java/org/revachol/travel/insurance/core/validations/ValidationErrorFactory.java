package org.revachol.travel.insurance.core.validations;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.core.util.ErrorCodeResolver;
import org.revachol.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ValidationErrorFactory {

    private final ErrorCodeResolver errorCodeResolver;

    ValidationError buildError(String errorCode) {
        String errorDescription = errorCodeResolver.getErrorDescription(errorCode);
        return new ValidationError(errorCode, errorDescription);
    }
}
