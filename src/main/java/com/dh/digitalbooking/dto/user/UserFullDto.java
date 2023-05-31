package com.dh.digitalbooking.dto.user;

import com.dh.digitalbooking.entity.Booking;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.entity.Rating;

import java.util.List;

public record UserFullDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        List<Booking> bookings,
        List<Rating> ratings,
        List<Producto> favorites
) {
}
