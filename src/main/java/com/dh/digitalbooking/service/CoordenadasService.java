package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Coordenadas;
import org.springframework.stereotype.Service;

@Service
public interface CoordenadasService {
    Coordenadas getByIdCoordenadas(Long id);
}
