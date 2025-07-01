package org.revachol.travel.insurance.core.validations;

import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonsFirstNameValidationTest {
    private PersonsFirstNameValidation validation = new PersonsFirstNameValidation();

    @Test
    public void shouldReturnErrorWhenFirstNameNull() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn(null);
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personFirstName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }
    @Test
    public void shouldReturnErrorWhenFirstNameEmpty() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getPersonFirstName()).thenReturn("");
        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertEquals("personFirstName", errorOpt.get().getField());
        assertEquals("Must not be empty!", errorOpt.get().getMessage());
    }
}
