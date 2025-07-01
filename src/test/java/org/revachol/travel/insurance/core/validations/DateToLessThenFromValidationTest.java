package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DateToLessThenFromValidationTest {
    private DateToLessThenFromValidation validation = new DateToLessThenFromValidation();

    @Test
    public void shouldReturnErrorWhenDateToLessThenFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 03, 20));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 02, 10));
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("agreementDateTo", errorOpt.get().getField());
        assertEquals("Must be less then agreementDateTo!", errorOpt.get().getMessage());
    }
    @Test
    public void shouldReturnErrorWhenDateToEqualsFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 03, 20));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 03, 20));
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("agreementDateTo", errorOpt.get().getField());
        assertEquals("Must be less then agreementDateTo!", errorOpt.get().getMessage());


    }
}
