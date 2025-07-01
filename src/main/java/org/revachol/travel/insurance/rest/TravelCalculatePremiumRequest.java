package org.revachol.travel.insurance.rest;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TravelCalculatePremiumRequest {

    private String personFirstName;
    private String personLastName;
    private LocalDate agreementDateFrom;

    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate agreementDateTo;

    @JsonProperty("selected_risks")
    private List<String> selectedRisks;

}
