package com.dh.digitalbooking.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CountryFullDto(
        @NotNull(message = "Country ID is required")
        Long id,
        @NotBlank(message = "Country name is required")
        @Size(max = 100, message = "Country name cannot be longer than 100 characters")
        String name)
{}
