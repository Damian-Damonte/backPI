package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Pais;
import com.dh.digitalbooking.model.Puntuacion;

import java.util.List;

public interface PuntuacionService {
    List<Puntuacion> allPuntuacion();
    Puntuacion getByIdPuntuacion(Long id);
    Puntuacion savePuntuacion(Puntuacion puntuacion);
    void deletePuntuacion(Long id);
    Puntuacion updatePuntuacion(Puntuacion puntuacion);
}
