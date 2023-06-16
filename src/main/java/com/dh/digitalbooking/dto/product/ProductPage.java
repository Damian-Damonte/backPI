package com.dh.digitalbooking.dto.product;

import java.util.List;

public record ProductPage(
        List<ProductResponse> content,
        int totalPages,
        int currentPage,
        Long totalElements
) {
}
