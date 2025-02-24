package com.PayverdeAssessment.validation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FetchTransactionLogsValidation {

    @NotNull(message = "user id is required")
    private long userId;
}
