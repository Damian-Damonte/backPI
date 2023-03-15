package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.model.Pais;
import com.dh.digitalbooking.model.Puntuacion;

import java.util.List;

public interface PuntuacionService {
    List<Puntuacion> allPuntuacion();
    Puntuacion getByIdPuntuacion(Long id);
    Puntuacion savePuntuacion(Puntuacion puntuacion, UserDetailsDto userDetailsDto);
    void deletePuntuacion(Long id);
    Puntuacion updatePuntuacion(Puntuacion puntuacion);
}
