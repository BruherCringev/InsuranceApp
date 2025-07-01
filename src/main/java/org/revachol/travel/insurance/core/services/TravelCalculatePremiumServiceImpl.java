package org.revachol.travel.insurance.core.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.revachol.travel.insurance.core.underwriting.TravelPremiumUnderwriting;
import org.revachol.travel.insurance.core.validations.TravelCalculatePremiumRequestValidator;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumRequest;
import org.revachol.travel.insurance.dto.TravelCalculatePremiumResponse;
import org.revachol.travel.insurance.dto.ValidationError;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
@Component
class TravelCalculatePremiumServiceImpl implements TravelCalculatePremiumService {

    private final TravelCalculatePremiumRequestValidator requestValidator;
    private final TravelPremiumUnderwriting premiumUnderwriting;


    @Override
    public TravelCalculatePremiumResponse calculatePremium(TravelCalculatePremiumRequest request) {
        List<ValidationError> errors = requestValidator.validate(request);
        return errors.isEmpty()
                ? buildResponse(request, premiumUnderwriting.calculatePremium(request))
                : buildResponse(errors);

        }
        private TravelCalculatePremiumResponse buildResponse(List<ValidationError> errors) {
            return new TravelCalculatePremiumResponse(errors);
        }

        private TravelCalculatePremiumResponse buildResponse(TravelCalculatePremiumRequest request, BigDecimal premiumUnderwriting){
            TravelCalculatePremiumResponse response = new TravelCalculatePremiumResponse();
            response.setPersonFirstName(request.getPersonFirstName());
            response.setPersonLastName(request.getPersonLastName());
            response.setAgreementDateFrom(request.getAgreementDateFrom());
            response.setAgreementDateTo(request.getAgreementDateTo());
            response.setAgreementPrice(premiumUnderwriting);

            return response;
        }


    }








