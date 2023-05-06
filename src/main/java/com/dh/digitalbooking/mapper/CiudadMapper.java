package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.ciudad.CityPutDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadPostDTO;
import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.entity.Ciudad;
import com.dh.digitalbooking.entity.Pais;
import org.springframework.stereotype.Component;

@Component
public class CiudadMapper {
    public CiudadDTO ciudadToCiudadDTO(Ciudad ciudad) {
        PaisDTO paisDTO = new PaisDTO(ciudad.getPais().getId(), ciudad.getPais().getNombre());
        return new CiudadDTO(ciudad.getId(), ciudad.getNombre(), paisDTO);
    }

    public Ciudad ciudadPostDTOToCiudad(CiudadPostDTO ciudadPostDTO) {
        Pais pais = new Pais();
        pais.setId(ciudadPostDTO.paisId());

        Ciudad ciudad = new Ciudad();
        ciudad.setNombre(ciudadPostDTO.nombre());
        ciudad.setPais(pais);
        return ciudad;
    }

    public Ciudad cityPutDTOToCity(CityPutDTO cityPutDTO) {
        Pais pais = new Pais();
        pais.setId(cityPutDTO.paisId());

        Ciudad ciudad = new Ciudad();
        ciudad.setId(cityPutDTO.id());
        ciudad.setNombre(cityPutDTO.nombre());
        ciudad.setPais(pais);
        return ciudad;
    }
}
