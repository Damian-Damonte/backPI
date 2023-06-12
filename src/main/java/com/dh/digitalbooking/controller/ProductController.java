package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.product.*;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.service.imp.ProductServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {
    private final ProductServiceImp productoServiceImp;

    public ProductController(ProductServiceImp productoServiceImp) {
        this.productoServiceImp = productoServiceImp;
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllPage(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(productoServiceImp.getAllPage(page));
    }

    @GetMapping("/filters")
    @Operation(
            summary = "Productos filtrados por city, categoría, fecha checkIn y fecha checkOut",
            description = "En caso de no enviar ningún parámetro devolverá todos los productos. " +
                    "No es necesario enviar todos los parámetros para para poder realizar un filtrado."
    )
    public ResponseEntity<ProductPageDto> getAllFilters(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "cityId", required = false) Long cityId,
            @RequestParam(name = "categoryId",required = false) Long categoryId,
            @RequestParam(name = "checkIn",required = false) LocalDate checkIn,
            @RequestParam(name = "checkOut",required = false) LocalDate checkOut
            ) {
        return ResponseEntity.ok(productoServiceImp.getByAllFilters(page, cityId, categoryId, checkIn, checkOut));
    }


    @GetMapping("/random")
    @Operation(summary = "Devuelve 4 productos de forma aleatoria")
    public ResponseEntity<List<Product>> getRandomProduct() {
        return ResponseEntity.ok(productoServiceImp.getRandomProductos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFullDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productoServiceImp.getProductoById(id));
    }

    @PostMapping
    @Operation(description = "Al crear un product el promedio de puntuaciones siempre será null")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServiceImp.saveProducto(productRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Al eliminar el product se eliminarán automaticamente las imagenes, puntuaciones y politicas relacionadas a este. " +
            "El product no podrá ser eliminado si cuenta con reservas")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productoServiceImp.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "El promedio de puntuaciones y las reservas de un product no puede ser modificadas a través de este endpoint. " +
            "Cuando se modifique un product se pueden omitir dichos atributos")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody @Valid ProductUpdate productUpdate) {
        return ResponseEntity.ok(productoServiceImp.updateProducto(id, productUpdate));
    }
}