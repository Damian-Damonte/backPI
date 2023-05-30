package com.dh.digitalbooking.dto.city;

import com.dh.digitalbooking.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityRequest(
        @NotBlank(message = "City name is required")
        @Size(max = 100, message = "City name cannot be longer than 100 characters")
        String name,
        @NotNull(message = "Country id is required")
        @Valid
        OnlyId country
) {
}
