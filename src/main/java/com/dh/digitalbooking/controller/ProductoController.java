package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.service.imp.ProductoServiceImp;
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
public class ProductoController {
    private final ProductoServiceImp productoServiceImp;

    public ProductoController(ProductoServiceImp productoServiceImp) {
        this.productoServiceImp = productoServiceImp;
    }

    @GetMapping
    public ResponseEntity<Page<Producto>> getAllPage(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(productoServiceImp.getAllPage(page));
    }

    @GetMapping("/filters")
    @Operation(
            summary = "Productos filtrados por ciudad, categoría, fecha checkIn y fecha checkOut",
            description = "En caso de no enviar ningún parámetro devolverá todos los productos. " +
                    "No es necesario enviar todos los parámetros para para poder realizar un filtrado."
    )
    public ResponseEntity<ProductPageDto> getAllFilters(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "ciudadId", required = false) Long ciudadId,
            @RequestParam(name = "categoriaId",required = false) Long categoriaId,
            @RequestParam(name = "checkIn",required = false) LocalDate checkIn,
            @RequestParam(name = "checkOut",required = false) LocalDate checkOut
            ) {
        ProductoFilterRequest filters = new ProductoFilterRequest(ciudadId, categoriaId, checkIn, checkOut);

        return ResponseEntity.ok(productoServiceImp.getByAllFilters(page, filters));
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
    @Operation(description = "Al crear un producto el promedio de puntuaciones siempre será null")
    public ResponseEntity<Producto> saveProducto(@RequestBody @Valid Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServiceImp.saveProducto(producto));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Al eliminar el producto se eliminarán automaticamente las imagenes, puntuaciones y politicas relacionadas a este. " +
            "El producto no podrá ser eliminado si cuenta con reservas")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoServiceImp.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(description = "El promedio de puntuaciones y las reservas de un producto no puede ser modificadas a través de este endpoint. " +
            "Cuando se modifique un producto se pueden omitir dichos atributos")
    public ResponseEntity<Producto> updateProducto(@RequestBody @Valid Producto producto) {
        return ResponseEntity.ok(productoServiceImp.updateProducto(producto));
    }
}
