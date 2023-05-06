package com.dh.digitalbooking.dto.pais;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CountryFullDTO(
        @NotNull(message = "Country ID is required")
        Long id,
        @NotBlank(message = "Country name is required")
        @Size(max = 45, message = "Country name cannot be longer than 45 characters")
        String name){
}
