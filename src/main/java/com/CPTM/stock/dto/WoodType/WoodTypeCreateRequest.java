package com.CPTM.stock.dto.WoodType;

import jakarta.validation.constraints.NotBlank;

public record WoodTypeCreateRequest(
        @NotBlank
        String name,
        String description
) {
}
