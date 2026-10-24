package com.CPTM.stock.dto.Sleeper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record SleeperCreateRequest(
        @NotBlank
        String identifier,
        @NotNull
        UUID woodTypeId,
        @NotNull
        UUID stockLocationId,
        @NotNull
        @Positive
        BigDecimal lengthMeters,
        @NotNull
        @Positive
        BigDecimal widthMeters,
        @NotNull
        @Positive
        BigDecimal heightMeters
) {
}
