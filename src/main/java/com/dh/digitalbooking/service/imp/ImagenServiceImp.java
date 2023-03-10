package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Imagen;
import com.dh.digitalbooking.repository.ImagenRepository;
import com.dh.digitalbooking.service.ImagenService;
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
