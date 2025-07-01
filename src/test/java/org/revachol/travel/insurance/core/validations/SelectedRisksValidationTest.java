package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SelectedRisksValidationTest {
    private SelectedRisksValidation validation = new SelectedRisksValidation();

    @Test
    public void shouldReturnErrorWhenSelectedRisksNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("selectedRisks", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }
    @Test
    public void shouldReturnErrorWhenSelectedRisksEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("selectedRisks", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }
}
