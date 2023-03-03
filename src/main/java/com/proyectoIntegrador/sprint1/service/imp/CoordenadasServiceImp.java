package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Coordenadas;
import com.proyectoIntegrador.sprint1.repository.CoordenadasRepository;
import com.proyectoIntegrador.sprint1.service.CoordenadasService;
import org.springframework.stereotype.Service;

@Service
public class CoordenadasServiceImp implements CoordenadasService {
    private final CoordenadasRepository coordenadasRepository;

    public CoordenadasServiceImp(CoordenadasRepository coordenadasRepository) {
        this.coordenadasRepository = coordenadasRepository;
    }

    @Override
    public Coordenadas getByIdCoordenadas(Long id) {
        return coordenadasRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Coordenadas con id " + id + " no encontradas")
        );
    }
}
