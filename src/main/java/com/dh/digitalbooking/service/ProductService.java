package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.product.*;
import com.dh.digitalbooking.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducto();
    Page<Product> getAllPage(int page);
    ProductPageDto getByAllFilters(int page, ProductFilterRequest filters);
    List<Product> getRandomProductos();
    ProductFullDto getProductoById(Long id);
    ProductResponse saveProducto(ProductRequest productRequest);
    void deleteProducto(Long id);
    ProductResponse updateProducto(Long id, ProductUpdate productUpdate);
    boolean existByCityId(Long id);
}
