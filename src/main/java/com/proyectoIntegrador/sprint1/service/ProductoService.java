package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> allProductos();
    Producto getProductoById(Long id);
    Producto saveProducto(Producto producto);
    void deleteProducto(Long id);
    Producto updateProducto(Producto updateProducto);
}
