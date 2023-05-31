package com.dh.digitalbooking.dto.user;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city
) {
}
