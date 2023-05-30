package com.dh.digitalbooking.dto.policyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PolicyTypeRequest(
        @NotBlank(message = "Policy type name requiered")
        @Size(max = 255, message = "Policy type name cannot be longer than 255 characters")
        String name
) {}
