package com.dh.digitalbooking.dto.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityPutDTO(
        @NotNull(message = "City ID is required")
        Long id,
        @NotBlank(message = "City name is required")
        @Size(max = 100, message = "City name cannot be longer than 100 characters")
        String name,
        @NotNull(message = "Country ID is required")
        Long countryId
) {
}
