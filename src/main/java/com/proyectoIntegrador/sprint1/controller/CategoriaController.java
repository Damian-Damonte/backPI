package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.service.imp.CategoriaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
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
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaServiceImp.saveCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaServiceImp.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaServiceImp.updateCategoria(categoria));
    }
}
