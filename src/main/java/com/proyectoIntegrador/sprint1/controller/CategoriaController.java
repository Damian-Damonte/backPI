package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.ok(categoriaService.allCategoria());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @PostMapping
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.saveCategoria(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Categoria> updateCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.updateCategoria(categoria));
    }
}
