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
public class PersonsFirstNameValidationTest {

    @Mock
    private ErrorCodeResolver errorCodeResolver;

    @InjectMocks
    private PersonsFirstNameValidation validation;

    @Test
    public void shouldReturnErrorWhenFirstNameNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_5"))
                .thenReturn("Field personFirstName must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_5", errorOpt.get().getErrorCode());
        assertEquals("Field personFirstName must not be empty!", errorOpt.get().getDescription());
    }
    @Test
    public void shouldReturnErrorWhenFirstNameEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        when(errorCodeResolver.getErrorDescription("ERROR_CODE_5"))
                .thenReturn("Field personFirstName must not be empty!");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("ERROR_CODE_5", errorOpt.get().getErrorCode());
        assertEquals("Field personFirstName must not be empty!", errorOpt.get().getDescription());
    }
}
