package com.CPTM.stock.dto.Sleeper;

import com.CPTM.stock.domain.SleeperStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SleeperResponse(
        UUID id,
        String identifier,
        UUID woodTypeId,
        UUID stockLocationId,
        BigDecimal lengthMeters,
        BigDecimal widthMeters,
        BigDecimal heightMeters,
        SleeperStatus status,
        LocalDateTime createdAt
) {
}
