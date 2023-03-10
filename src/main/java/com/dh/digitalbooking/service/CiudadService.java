package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Ciudad;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CiudadService {
    List<Ciudad> allCiudad();
    Ciudad getByIdCiudad(Long id);
    Ciudad saveCiudad(Ciudad ciudad);
    void deleteCiudad(Long id);
    Ciudad updateCiudad(Ciudad ciudad);
}
