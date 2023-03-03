package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Imagen;
import com.proyectoIntegrador.sprint1.repository.ImagenRepository;
import com.proyectoIntegrador.sprint1.service.ImagenService;
import org.springframework.stereotype.Service;

@Service
public class ImagenServiceImp implements ImagenService {
    private final ImagenRepository imagenRepository;

    public ImagenServiceImp(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Imagen getByIdImagen(Long id) {
        return imagenRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Imagen con id " + id + " no encontrada")
        );
    }
}
