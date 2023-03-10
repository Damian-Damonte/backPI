package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> allCategoria();
    Categoria getCategoriaById(Long id);
    Categoria saveCategoria(Categoria categoria);
    void deleteCategoria(Long id);
    Categoria updateCategoria(Categoria updateCategoria);
}
