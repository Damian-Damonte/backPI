package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Caracteristica;
import java.util.List;

public interface CaracteristicaService {
    List<Caracteristica> getAllCaracteristica();
    Caracteristica getByIdCaracteristica(Long id);
    Caracteristica saveCaracteristica(Caracteristica caracteristica);
    void deleteCaracteristica(Long id);
    Caracteristica updateCaracteristica(Caracteristica caracteristica);
}
