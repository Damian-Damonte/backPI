package com.dh.digitalbooking.dto.user;

import com.dh.digitalbooking.dto.product.ProductResponse;
import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.entity.User;

import java.util.List;

public record UserFullDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String city,
        List<RatingFullDto> ratings,
        List<ProductResponse> favorites,
        List<ProductResponse> products,
        User.Role role
) {
}
