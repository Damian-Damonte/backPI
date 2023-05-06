package com.dh.digitalbooking.dto.policy;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PolicyPostDTO(
        @Size(max = 600, message = "Policy description cannot be longer than 600 characters")
        @NotBlank(message = "Policy description required")
        String description,
        @NotNull(message = "Policy type ID is required")
        Long policyTypeId
) {
}
