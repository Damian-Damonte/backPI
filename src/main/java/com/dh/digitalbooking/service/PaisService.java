package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import java.util.List;

public interface PaisService {
    List<PaisDTO> allPais();
    PaisDTO getByIdPais(Long id);
    PaisDTO savePais(PaisDTO pais);
    void deletePais(Long id);
    PaisDTO updatePais(PaisDTO pais);
}
