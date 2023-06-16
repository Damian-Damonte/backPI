package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.product.*;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProductNoPage();
    ProductPage getWithFilters(int page, Long cityId, Long categoryId, LocalDate checkIn, LocalDate checkout);
    List<ProductResponse> getRandomProducts();
    ProductFullDto getProductById(Long id);
    ProductResponse saveProduct(ProductRequest productRequest);
    void deleteProduct(Long id);
    ProductResponse updateProduct(Long id, ProductUpdate productUpdate);
    boolean existByCityId(Long id);
}
