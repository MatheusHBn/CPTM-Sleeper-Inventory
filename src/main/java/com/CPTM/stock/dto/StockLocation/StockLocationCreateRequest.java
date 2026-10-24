package com.CPTM.stock.dto.StockLocation;

import jakarta.validation.constraints.NotBlank;

public record StockLocationCreateRequest(
        @NotBlank
        String code,
        @NotBlank
        String name,
        String description
) {
}
