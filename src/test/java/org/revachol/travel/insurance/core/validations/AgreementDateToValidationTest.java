package org.revachol.travel.insurance.core.validations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revachol.travel.insurance.core.ErrorCodeResolver;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgreementDateToValidationTest {

    @Mock
    private ErrorCodeResolver errorCodeResolver;

    @InjectMocks
    private AgreementDateToValidation validation;

    @Test
    public void shouldReturnErrorWhenAgreementDateToNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateTo()).thenReturn(null);
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_2"))
                .thenReturn("Field agreementDateTo must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);

        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_2", errorOpt.get().getErrorCode());
        assertEquals("Field agreementDateTo must not be empty!", errorOpt.get().getDescription());
    }
}
