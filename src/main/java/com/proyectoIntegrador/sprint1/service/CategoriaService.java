package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> allCategoria();
    Categoria getCategoriaById(Long id);
    Categoria saveCategoria(Categoria categoria);
    void deleteCategoria(Long id);
    Categoria updateCategoria(Categoria updateCategoria);
}
