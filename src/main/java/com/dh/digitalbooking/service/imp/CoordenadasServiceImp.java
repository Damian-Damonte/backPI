package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Coordenadas;
import com.dh.digitalbooking.repository.CoordenadasRepository;
import com.dh.digitalbooking.service.CoordenadasService;
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
