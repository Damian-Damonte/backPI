package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.product.*;
import com.dh.digitalbooking.entity.Product;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProductNoPage();
    ProductPage getWithFilters(int page, Long cityId, Long categoryId, LocalDate checkIn, LocalDate checkout);
    List<ProductResponse> getRandomProducts();
    ProductFullDto getProductById(Long id);
    ProductResponse saveProduct(ProductRequest productRequest, Authentication authentication);
    void deleteProduct(Long id, Authentication authentication);
    ProductResponse updateProduct(Long id, ProductUpdate productUpdate, Authentication authentication);
    Product existById(Long id);
    boolean existByCityId(Long id);
}
