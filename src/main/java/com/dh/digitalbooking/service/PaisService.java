package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;

import java.util.List;

public interface PaisService {
    List<PaisDTO> allPais();
    PaisDTO getByIdPais(Long id);
    PaisDTO savePais(PaisNoIdDTO paisNoIdDTO);
    void deletePais(Long id);
    PaisDTO updatePais(PaisDTO pais);
}
