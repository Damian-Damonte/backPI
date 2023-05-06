package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.ciudad.CityPutDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadPostDTO;
import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.entity.Ciudad;
import com.dh.digitalbooking.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CiudadMapper {
    public CiudadDTO ciudadToCiudadDTO(Ciudad ciudad) {
        PaisDTO paisDTO = new PaisDTO(ciudad.getPais().getId(), ciudad.getPais().getName());
        return new CiudadDTO(ciudad.getId(), ciudad.getNombre(), paisDTO);
    }

    public Ciudad ciudadPostDTOToCiudad(CiudadPostDTO ciudadPostDTO) {
        Country country = new Country();
        country.setId(ciudadPostDTO.paisId());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(ciudadPostDTO.nombre());
        ciudad.setPais(country);
        return ciudad;
    }

    public Ciudad cityPutDTOToCity(CityPutDTO cityPutDTO) {
        Country country = new Country();
        country.setId(cityPutDTO.paisId());

        Ciudad ciudad = new Ciudad();
        ciudad.setId(cityPutDTO.id());
        ciudad.setNombre(cityPutDTO.nombre());
        ciudad.setPais(country);
        return ciudad;
    }
}
