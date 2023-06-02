package com.dh.digitalbooking.dto.product;

import com.dh.digitalbooking.dto.common.OnlyId;
import com.dh.digitalbooking.dto.image.ImageFullDto;
import com.dh.digitalbooking.dto.policy.PolicyFullDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record ProductUpdate(
        @NotBlank(message = "Title is required")
        @Size(max = 60, message = "Title cannot be longer than 60 characters")
        String title,
        @NotBlank(message = "Title description is required")
        @Size(max = 100, message = "Title description cannot be longer than 100 characters")
        String titleDescription,
        @NotBlank(message = "Description is required")
        @Size(max = 1200, message = "Description cannot be longer than 1200 characters")
        String description,
        @NotBlank(message = "Address is required")
        @Size(max = 255, message = "Title description cannot be longer than 255 characters")
        String address,
        @DecimalMin(value = "1.00",  message = "The price per night cannot be less than 1.00")
        @DecimalMax(value = "99999999.99", message = "The price per night cannot be higher than 99999999.99")
        @NotNull(message = "Price per night is required")
        BigDecimal pricePerNight,
        @NotNull(message = "Latitude is required")
        @DecimalMin(value = "-90.0",  message = "Latitude should not be less than -90.0")
        @DecimalMax(value = "90.0", message = "Latitude should not be greater than 90.0")
        BigDecimal latitude,
        @NotNull(message = "Longitude is required")
        @DecimalMin(value = "-180.0",  message = "Latitude should not be less than -180.0")
        @DecimalMax(value = "180.0", message = "Latitude should not be greater than 180.0")
        BigDecimal longitude,
        @NotNull(message = "Category is required")
        @Valid
        OnlyId category,
        @NotNull(message = "City is required")
        @Valid
        OnlyId city,
        @Valid
        @NotNull(message = "Product must have at least one amenity")
        @Size(max = 50, min = 1,message = "The product must have between 1 and 50 amenities")
        Set<OnlyId> amenities,
        @Size(max = 25, min = 1,message = "The product must have between 1 and 25 images")
        @NotNull(message = "Product must have at least one image")
        @Valid
        Set<ImageFullDto> images,
        @Valid
        @Size(max = 20, min = 1,message = "The product must have between 1 and 20 policies")
        Set<PolicyFullDto> policies
) {
}
