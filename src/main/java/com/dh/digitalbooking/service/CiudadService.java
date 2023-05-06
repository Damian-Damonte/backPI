package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.ciudad.CityPutDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadPostDTO;
import com.dh.digitalbooking.entity.Ciudad;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CiudadService {
    List<CiudadDTO> allCiudad();
    CiudadDTO getByIdCiudad(Long id);
    CiudadDTO saveCiudad(CiudadPostDTO ciudadPostDTO);
    void deleteCiudad(Long id);
    CiudadDTO updateCiudad(CityPutDTO cityPutDTO);
    boolean existsCityByCountryId(Long id);
}
