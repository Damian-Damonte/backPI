package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.model.Producto;

import java.util.List;

public interface PaisService {
    List<Pais> allPais();
    Pais getByIdPais(Long id);
    Pais savePais(Pais pais);
    void deletePais(Long id);
    Pais updatePais(Pais pais);
}
