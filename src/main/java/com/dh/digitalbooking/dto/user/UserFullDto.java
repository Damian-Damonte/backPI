package com.dh.digitalbooking.dto.user;

import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.entity.Rating;
import com.dh.digitalbooking.entity.User;

import java.util.List;

public record UserFullDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        List<Booking> bookings,
        List<Rating> ratings,
        List<Product> favorites,
        User.Role role
) {
}
