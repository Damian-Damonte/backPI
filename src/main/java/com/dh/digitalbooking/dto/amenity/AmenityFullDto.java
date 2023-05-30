package com.dh.digitalbooking.dto.amenity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AmenityFullDto(
        @NotNull(message = "Amenity ID is required")
        Long id,
        @NotBlank(message = "Amenity name is required")
        @Size(max = 255, message = "Amenity name cannot be longer than 255 characters")
        String name
        ) {
}
