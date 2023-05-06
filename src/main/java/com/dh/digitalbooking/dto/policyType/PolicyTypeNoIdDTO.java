package com.dh.digitalbooking.dto.policyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PolicyTypeNoIdDTO(
        @NotBlank(message = "Policy name requiered")
        @Size(max = 255, message = "Policy name cannot be longer than 255 characters")
        String name
) {}
