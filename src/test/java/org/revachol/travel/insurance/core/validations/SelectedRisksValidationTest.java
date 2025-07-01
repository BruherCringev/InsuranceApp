package org.revachol.travel.insurance.core.validations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revachol.travel.insurance.core.ErrorCodeResolver;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SelectedRisksValidationTest {
    @Mock
    private ErrorCodeResolver errorCodeResolver;

    @InjectMocks
    private SelectedRisksValidation validation;

    @Test
    public void shouldReturnErrorWhenSelectedRisksNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(null);
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_4"))
                .thenReturn("Field selectedRisks must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_4", errorOpt.get().getErrorCode());
        assertEquals("Field selectedRisks must not be empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenSelectedRisksEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getSelectedRisks()).thenReturn(List.of());
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_4"))
                .thenReturn("Field selectedRisks must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_4", errorOpt.get().getErrorCode());
        assertEquals("Field selectedRisks must not be empty!", errorOpt.get().getDescription());
    }
}
