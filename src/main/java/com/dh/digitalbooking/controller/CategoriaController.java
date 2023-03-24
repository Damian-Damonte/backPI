package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.model.Categoria;
import com.dh.digitalbooking.service.imp.CategoriaServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaServiceImp categoriaServiceImp;

    public CategoriaController(CategoriaServiceImp categoriaServiceImp) {
        this.categoriaServiceImp = categoriaServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaServiceImp.allCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoriaServiceImp.getCategoriaById(id));
    }

    @PostMapping
    public ResponseEntity<Categoria> saveCategoria(@RequestBody @Valid Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServiceImp.saveCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaServiceImp.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(
            description = "No se puede actualizar la cantidad de productos que tiene la categoria a traves de este endpoint. " +
                    "Este atributo depende de la cantidad de productos existentes de de dicha categoria."
    )
    public ResponseEntity<Categoria> updateCategoria(@RequestBody @Valid Categoria categoria) {
        return ResponseEntity.ok(categoriaServiceImp.updateCategoria(categoria));
    }
}
