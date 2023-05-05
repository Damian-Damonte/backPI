package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> allCategoria();
    Categoria getCategoriaById(Long id);
    Categoria saveCategoria(Categoria categoria);
    void deleteCategoria(Long id);
    Categoria updateCategoria(Categoria updateCategoria);
    void sumarProducto(Long id);
    void restarProducto(Long id);
}
