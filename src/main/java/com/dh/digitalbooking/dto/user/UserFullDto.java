package com.dh.digitalbooking.dto.user;

import com.dh.digitalbooking.dto.booking.BookingResponse;
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
        List<BookingResponse> bookings,
        List<Rating> ratings,
        List<Product> favorites,
        User.Role role
) {
}
