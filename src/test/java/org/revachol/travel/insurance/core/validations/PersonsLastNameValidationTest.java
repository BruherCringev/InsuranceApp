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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonsLastNameValidationTest {
    @Mock
    private ErrorCodeResolver errorCodeResolver;

    @InjectMocks
    private PersonsLastNameValidation validation;

    @Test
    public void shouldReturnErrorWhenLastNameNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn(null);
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_6"))
                .thenReturn("Field personLastName must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_6", errorOpt.get().getErrorCode());
        assertEquals("Field personLastName must not be empty!", errorOpt.get().getDescription());
    }

    @Test
    public void shouldReturnErrorWhenLastNameEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonLastName()).thenReturn("");
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_6"))
                .thenReturn("Field personLastName must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_6", errorOpt.get().getErrorCode());
        assertEquals("Field personLastName must not be empty!", errorOpt.get().getDescription());
    }
}