package com.dh.digitalbooking.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest (
        @NotBlank(message = "Category name is required")
        @Size(max = 45, message = "Category name cannot be longer than 45 characters")
        String name,
        @NotBlank(message = "Category description is required")
        @Size(max = 255, message = "Category description cannot be longer than 255 characters")
        String description,
        @NotBlank(message = "Category image url is required")
        @Size(max = 255, message = "Category image url cannot be longer than 255 characters")
        String imageUrl
){
}
