package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Imagen;

import java.util.List;

public interface ImagenService {
    List<Imagen> allImagen();
    Imagen getByIdImagen(Long id);
    Imagen saveImagen(Imagen imagen);
    void deleteImagen(Long id);
    Imagen updateImagen(Imagen imagen);
}
