package org.revachol.travel.insurance.core.services;


import org.revachol.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.revachol.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class TravelCalculatePremiumServiceImplTest {

    @Mock
    private TravelCalculatePremiumRequestValidator requestValidator;
    @Mock
    private TravelPremiumUnderwriting premiumUnderwriting;

    @InjectMocks
    private TravelCalculatePremiumServiceImpl service;

    @Test
    public void shouldReturnPersonsFirstName() {
        var request = createFields();
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(25));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(request.getPersonFirstName(), response.getPersonFirstName());
    }

    @Test
    public void shouldReturnPersonsLastName() {
        var request = createFields();
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(25));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(request.getPersonLastName(), response.getPersonLastName());
    }

    @Test
    public void shouldReturnCorrectAgreementDateFrom() {
        var request = createFields();
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(25));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(request.getAgreementDateFrom(), response.getAgreementDateFrom());
    }

    @Test
    public void shouldReturnCorrectAgreementDateTo() {
        var request = createFields();
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(25));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(request.getAgreementDateTo(), response.getAgreementDateTo());
    }

    @Test
    public void shouldReturnCorrectAgreementPrice() {
        var request = createFields();
        when(premiumUnderwriting.calculatePremium(request)).thenReturn(BigDecimal.valueOf(25));
        when(requestValidator.validate(request)).thenReturn(List.of());

        var response = service.calculatePremium(request);

        assertEquals(BigDecimal.valueOf(25), response.getAgreementPrice());
    }

    @Test
    public void shouldReturnResponseWithErrors() {
        var request = createFields();
        var validationError = new ValidationError("ERROR_CODE_1", "description");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        var response = service.calculatePremium(request);

        assertTrue(response.hasErrors());
    }

    @Test
    public void shouldReturnTheCorrectError() {
        var request = createFields();
        var validationError = new ValidationError("ERROR_CODE_1", "description");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        var response = service.calculatePremium(request);

        assertEquals("ERROR_CODE_1", response.getErrors().getFirst().getErrorCode());
        assertEquals("description", response.getErrors().getFirst().getDescription());
        assertNull(response.getPersonFirstName());
    }

    @Test
    public void shouldReturnResponseWithCorrectErrorCount() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("ERROR_CODE_1", "description");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        var response = service.calculatePremium(request);

        assertEquals(1, response.getErrors().size());
    }

    @Test
    public void allFieldsMustBeEmptyWhenResponseContainsError() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("ERROR_CODE_1", "description");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        var response = service.calculatePremium(request);

        assertNull(response.getPersonFirstName());
        assertNull(response.getPersonLastName());
        assertNull(response.getAgreementDateFrom());
        assertNull(response.getAgreementDateTo());
        assertNull(response.getAgreementPrice());
    }

    @Test
    public void shouldNotInteractWithPremiumUnderwritingWhenErrorsPresent() {
        var request = new TravelCalculatePremiumRequest();
        var validationError = new ValidationError("ERROR_CODE_1", "description");
        when(requestValidator.validate(request)).thenReturn(List.of(validationError));

        var response = service.calculatePremium(request);

        verifyNoInteractions(premiumUnderwriting);
    }

    private TravelCalculatePremiumRequest createFields() {
        var request = new TravelCalculatePremiumRequest();
        request.setPersonFirstName("Greg");
        request.setPersonLastName("Ismailov");
        request.setAgreementDateFrom(LocalDate.of(2025, 3, 31));
        request.setAgreementDateTo(LocalDate.of(2025, 4, 25));
        return request;
    }
}