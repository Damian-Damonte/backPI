package com.dh.digitalbooking.dto.amenity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AmenityOnlyNameDTO(
        @NotBlank(message = "Amenity name is required")
        @Size(max = 100, message = "Amenity name cannot be longer than 100 characters")
        String name
) {
}
