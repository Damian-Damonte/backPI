package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProducto();
    List<Producto> getAllWithFilters(Long ciudadId, Long categoriaId);
    List<Producto> getRandomProductos();
    Producto getProductoById(Long id);
    Producto saveProducto(Producto producto);
    void deleteProducto(Long id);
    Producto updateProducto(Producto updateProducto);
}
