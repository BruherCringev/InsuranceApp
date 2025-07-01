package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgreementDateFromValidationTest {
    private AgreementDateFromValidation validation = new AgreementDateFromValidation();

    @Test
    public void shouldReturnErrorWhenAgreementDateFromNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("agreementDateFrom", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }
}
