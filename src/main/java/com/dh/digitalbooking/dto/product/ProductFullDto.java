package com.dh.digitalbooking.dto.product;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.dto.category.CategoryFullDto;
import com.dh.digitalbooking.dto.city.CityFullDto;
import com.dh.digitalbooking.dto.image.ImageFullDto;
import com.dh.digitalbooking.dto.policy.PolicyFullDto;
import com.dh.digitalbooking.entity.Booking;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

public record ProductFullDto(
        Long id,
        String title,
        String titleDescription,
        String description,
        String address,
        BigDecimal pricePerNight,
        BigDecimal averageRating,
        BigDecimal latitude,
        BigDecimal longitude,
        CategoryFullDto category,
        CityFullDto city,
        List<AmenityFullDto> amenities,
        List<ImageFullDto> images,
        List<PolicyFullDto> policies,
        @JsonIgnoreProperties("product")
        List<Booking> bookings
) {
}
