package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.pais.CountryFullDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;

import java.util.List;

public interface PaisService {
    List<CountryFullDTO> allPais();
    CountryFullDTO getByIdPais(Long id);
    CountryFullDTO savePais(PaisNoIdDTO paisNoIdDTO);
    void deletePais(Long id);
    CountryFullDTO updatePais(CountryFullDTO pais);
}
