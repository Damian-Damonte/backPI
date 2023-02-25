package com.proyectoIntegrador.sprint1.projection;

import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.model.Ciudad;

public interface ProductoProjection {
    Long getId();
    String getTitulo();
    Categoria getCategoria();
    Ciudad getCiudad();
}
