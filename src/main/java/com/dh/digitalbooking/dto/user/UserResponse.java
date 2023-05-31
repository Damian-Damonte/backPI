package com.dh.digitalbooking.dto.user;

import com.dh.digitalbooking.entity.User;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        User.Role role
) {
}
