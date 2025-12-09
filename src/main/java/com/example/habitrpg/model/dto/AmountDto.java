package com.example.habitrpg.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AmountDto(
        @NotNull(message = "Amount must not be blank")
        @Positive
        Integer amount
) {
}
