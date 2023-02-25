package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.projection.ProductoProjection;

import java.util.List;

public interface ProductoService {
    List<Producto> getAllProducto();
    List<ProductoProjection> getAllProductoReduced();
    Producto getProductoById(Long id);
    Producto saveProducto(Producto producto);
    void deleteProducto(Long id);
    Producto updateProducto(Producto updateProducto);
}
