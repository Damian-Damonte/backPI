package com.dh.digitalbooking.dto.image;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ImageRequest(
        @Size(max = 100, message = "Image title cannot be longer than 100 characters")
        @NotBlank(message = "Image title is required")
        String title,

        @Size(max = 255, message = "Image url cannot be longer than 255 characters")
        @NotBlank(message = "Image url is required")
        String url,
        @Min(value = 0, message = "The order image value cannot be less than 0")
        @Max(value = 100, message = "The order image value cannot be greater than 100")
        Integer order
) {
}
