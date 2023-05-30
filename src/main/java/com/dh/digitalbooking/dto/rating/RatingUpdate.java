package com.dh.digitalbooking.dto.rating;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingUpdate(
        @NotNull(message = "Value is required")
        @Min(value = 1, message = "The rating value should not be less than 1")
        @Max(value = 5, message = "The rating value should not greater than 5")
        Integer value
) {
}
