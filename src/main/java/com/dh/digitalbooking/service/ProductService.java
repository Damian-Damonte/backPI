package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.dto.product.ProductRequest;
import com.dh.digitalbooking.dto.product.ProductResponse;
import com.dh.digitalbooking.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducto();
    Page<Product> getAllPage(int page);
    ProductPageDto getByAllFilters(int page, ProductoFilterRequest filters);
    List<Product> getRandomProductos();
    ProductResponse getProductoById(Long id);
    Product saveProducto(Product product);
    ProductResponse saveProducto2(ProductRequest productRequest);
    void deleteProducto(Long id);
    Product updateProducto(Product updateProduct);
    boolean existByCityId(Long id);
}
