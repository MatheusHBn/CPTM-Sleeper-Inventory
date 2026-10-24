package com.CPTM.stock.dto.WoodType;

import java.util.UUID;

public record WoodTypeResponse(
        UUID id,
        String name,
        String description,
        boolean active
) {
}
