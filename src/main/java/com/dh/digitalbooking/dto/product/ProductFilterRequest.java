package com.dh.digitalbooking.dto.product;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record ProductFilterRequest(
        @Positive(message = "The city id must be a positive number")
        Long cityId,
        @FutureOrPresent(message = "Checkin date cannot be earlier than the actual date")
        Long categoryId,
        @FutureOrPresent(message = "Checkin date cannot be earlier than the actual date")
        LocalDate checkIn,
        @FutureOrPresent(message = "Checkout date cannot be earlier than the actual date")
        LocalDate checkOut
) {
}
