package com.dh.digitalbooking.dto.image;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ImageNoIdDTO(
        @Size(max = 100, message = "Image title is required")
        @NotBlank(message = "Image title cannot be longer than 100 characters")
        String title,
        @Size(max = 255, message = "Image url is required")
        @NotBlank(message = "Image url cannot be longer than 255 characters")
        String url,
        @Min(value = 0, message = "El valor del orden debe ser mayor o igual a 0")
        @Max(value = 100, message = "El valor del orden debe ser menor o igual a 100")
        Integer order
) {
}
