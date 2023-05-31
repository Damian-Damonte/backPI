package com.dh.digitalbooking.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
        @NotBlank(message = "Email is required")
        @Size(max = 100, message = "First name cannot be longer than 100 characters")
        String email,
        @NotBlank(message = "Password is required")
        @Size(max = 100, message = "Password cannot be longer than 100 characters")
        String password
) {
}
