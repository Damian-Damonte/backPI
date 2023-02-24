package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.service.imp.ProductoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoServiceImp productoServiceImp;

    public ProductoController(ProductoServiceImp productoServiceImp) {
        this.productoServiceImp = productoServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoServiceImp.allProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productoServiceImp.getProductoById(id));
    }

    @PostMapping
    public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServiceImp.saveProducto(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoServiceImp.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoServiceImp.updateProducto(producto));
    }
}
