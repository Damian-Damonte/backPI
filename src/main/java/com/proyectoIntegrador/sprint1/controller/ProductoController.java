package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.service.imp.ProductoServiceImp;
import io.swagger.v3.oas.annotations.Operation;
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
        return ResponseEntity.ok(productoServiceImp.getAllProducto());
    }

    @GetMapping("/filters")
    @Operation(
            summary = "Devuelve productos filtrados por ciudad y categoría",
            description = "En caso de no enviar ningún parámetro tendrá el mismo funcionamiento que el endpoint /api/productos"
    )
    public ResponseEntity<List<Producto>> getAllProductoWithFilters(
            @RequestParam(name = "ciudadId", required = false) Long ciudadId,
            @RequestParam(name = "categoriaId", required = false) Long categoriaId
    ) {
        return ResponseEntity.ok(productoServiceImp.getAllWithFilters(ciudadId, categoriaId));
    }

    @GetMapping("/random")
    @Operation(summary = "Devuelve 4 productos de forma aleatoria")
    public ResponseEntity<List<Producto>> getRandomProducto() {
        return ResponseEntity.ok(productoServiceImp.getRandomProductos());
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
    @Operation(description = "Al eliminar el producto se eliminarán automaticamente las imagenes y politicas relacionadas a este")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoServiceImp.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoServiceImp.updateProducto(producto));
    }
}
