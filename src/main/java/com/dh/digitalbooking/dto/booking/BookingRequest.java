package com.dh.digitalbooking.dto.booking;

import com.dh.digitalbooking.dto.common.OnlyId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookingRequest(
        @NotNull(message = "Check-in date is required")
        @FutureOrPresent(message = "The check-in date cannot be before the current date")
        LocalDate checkIn,
        @NotNull(message = "Check-out date is required")
        @FutureOrPresent(message = "The check-out date cannot be before the current date")
        LocalDate checkOut,
        @NotNull(message = "Arrival time is required")
        LocalTime arrivalTime,
        @NotBlank(message = "Current user city is required")
        @Size(max = 100, message = "Current user city name cannot be longer than 100 characters")
        String userCity,
        @Size(max = 500, message = "The additional note cannot be longer than 500 characters")
        String additionalNote,
        boolean covidVaccine,
        @Valid @NotNull(message = "Product is required")
        OnlyId product
) {
}
