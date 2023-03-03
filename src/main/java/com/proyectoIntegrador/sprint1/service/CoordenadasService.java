package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Coordenadas;
import org.springframework.stereotype.Service;

@Service
public interface CoordenadasService {
    Coordenadas getByIdCoordenadas(Long id);
}
