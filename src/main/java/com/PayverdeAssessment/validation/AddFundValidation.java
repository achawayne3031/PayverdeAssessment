package com.PayverdeAssessment.validation;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddFundValidation {

    @NotNull(message = "amount is required")
    private double amount;

    @NotNull(message = "beneficiary id is required")
    private long beneficiaryId;

}
