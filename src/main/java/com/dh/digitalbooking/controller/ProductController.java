package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.product.*;
import com.dh.digitalbooking.service.imp.ProductServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductServiceImp productoServiceImp;

    public ProductController(ProductServiceImp productoServiceImp) {
        this.productoServiceImp = productoServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllNoPage() {
        return ResponseEntity.ok(productoServiceImp.getAllProductNoPage());
    }

    @GetMapping("/filters")
    @Operation(
            summary = "Get products filtered by city ID, category ID, check-in date and check-out date",
            description = "You don't need to provide all parameters. If no parameters are specified, it will return all products"
    )
    public ResponseEntity<ProductPage> getWithFilters(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "cityId", required = false) Long cityId,
            @RequestParam(name = "categoryId",required = false) Long categoryId,
            @RequestParam(name = "checkIn",required = false) LocalDate checkIn,
            @RequestParam(name = "checkOut",required = false) LocalDate checkOut
            ) {
        return ResponseEntity.ok(productoServiceImp.getWithFilters(page, cityId, categoryId, checkIn, checkOut));
    }

    @GetMapping("/random")
    @Operation(summary = "Return 4 randomly products")
    public ResponseEntity<List<ProductResponse>> getRandomProducts() {
        return ResponseEntity.ok(productoServiceImp.getRandomProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFullDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productoServiceImp.getProductById(id));
    }

    @PostMapping
    @Operation(description = "When creating a product, the average rating will be null.")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoServiceImp.saveProduct(productRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "When deleting the product, its images, ratings, and policies will be automatically deleted as well. " +
            "The product cannot be deleted if it has any reservations.")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productoServiceImp.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(description = "The average rating and bookings of a product cannot be modified.")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
                                                         @RequestBody @Valid ProductUpdate productUpdate) {
        return ResponseEntity.ok(productoServiceImp.updateProduct(id, productUpdate));
    }
}
