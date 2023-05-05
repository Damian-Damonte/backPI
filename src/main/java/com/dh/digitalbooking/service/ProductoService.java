package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.entity.Producto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProducto();
    Page<Producto> getAllPage(int page);
    ProductPageDto getByAllFilters(int page, ProductoFilterRequest filters);
    List<Producto> getRandomProductos();
    Producto getProductoById(Long id);
    Producto saveProducto(Producto producto);
    void deleteProducto(Long id);
    Producto updateProducto(Producto updateProducto);
}
