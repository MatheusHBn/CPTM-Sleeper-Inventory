package com.CPTM.stock.dto.StockLocation;

import java.util.UUID;

public record StockLocationResponse(
        UUID id,
        String code,
        String name,
        String description,
        boolean active
) {
}
