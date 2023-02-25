package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.model.Caracteristica;
import com.proyectoIntegrador.sprint1.model.Pais;

import java.util.List;

public interface CaracteristicaService {
    List<Caracteristica> getAllCaracteristica();
    Caracteristica getByIdCaracteristica(Long id);
    Caracteristica saveCaracteristica(Caracteristica caracteristica);
    void deleteCaracteristica(Long id);
    Caracteristica updateCaracteristica(Caracteristica caracteristica);
}
