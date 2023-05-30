package com.dh.digitalbooking.dto.policy;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PolicyFullDto(
        @NotNull(message = "Policy ID is required")
        Long id,
        @Size(max = 600, message = "Policy description cannot be longer than 600 characters")
        @NotBlank(message = "Policy description required")
        String description,
        @NotNull(message = "Policy type required")
        PolicyTypeFullDto policyType
) {
}
