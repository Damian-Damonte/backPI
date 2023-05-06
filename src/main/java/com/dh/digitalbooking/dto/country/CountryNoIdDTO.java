package com.dh.digitalbooking.dto.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CountryNoIdDTO(
        @NotBlank(message = "Country name is required")
        @Size(max = 45, message = "Country name cannot be longer than 45 characters")
        String name){
}