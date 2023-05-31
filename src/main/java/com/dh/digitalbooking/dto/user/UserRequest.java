package com.dh.digitalbooking.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @Size(max = 100, message = "First name cannot be longer than 100 characters")
        @NotBlank(message = "First name is required")
        String firstName,
        @Size(max = 100, message = "Last name cannot be longer than 100 characters")
        @NotBlank(message = "Last name is required")
        String lastName,
        @Size(max = 255, message = "Email cannot be longer than 255 characters")
        @NotBlank(message = "Email is required")
        String email,
        @Size(max = 100, message = "Password cannot be longer than 100 characters")
        @NotBlank(message = "Password is required")
        String password
) {
}
