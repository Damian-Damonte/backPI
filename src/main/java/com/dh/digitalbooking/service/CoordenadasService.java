package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Coordenadas;
import org.springframework.stereotype.Service;

@Service
public interface CoordenadasService {
    Coordenadas getByIdCoordenadas(Long id);
}
