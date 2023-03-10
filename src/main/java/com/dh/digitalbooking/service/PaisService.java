package com.dh.digitalbooking.service;

import com.dh.digitalbooking.model.Pais;
import java.util.List;

public interface PaisService {
    List<Pais> allPais();
    Pais getByIdPais(Long id);
    Pais savePais(Pais pais);
    void deletePais(Long id);
    Pais updatePais(Pais pais);
}
