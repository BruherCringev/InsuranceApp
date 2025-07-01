package org.revachol.travel.insurance.core.validations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.revachol.travel.insurance.rest.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.rest.ValidationError;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DateToLessThenFromValidationTest {

    @Mock
    private ValidationErrorFactory errorFactory;

    @InjectMocks
    DateToLessThenFromValidation validation;

    @Test
    public void shouldReturnErrorWhenDateToLessThenFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);

        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 03, 20));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 02, 10));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_3"))
                .thenReturn(validationError);

        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }
    @Test
    public void shouldReturnErrorWhenDateToEqualsFrom() {
        TravelCalculatePremiumRequest request = mock(TravelCalculatePremiumRequest.class);
        when(request.getAgreementDateFrom()).thenReturn(LocalDate.of(2025, 03, 20));
        when(request.getAgreementDateTo()).thenReturn(LocalDate.of(2025, 03, 20));
        ValidationError validationError = mock(ValidationError.class);
        when(errorFactory.buildError("ERROR_CODE_3"))
                .thenReturn(validationError);

        Optional<ValidationError> errorOpt = validation.execute(request);
        assertTrue(errorOpt.isPresent());
        assertSame(errorOpt.get(), validationError);
    }
}
