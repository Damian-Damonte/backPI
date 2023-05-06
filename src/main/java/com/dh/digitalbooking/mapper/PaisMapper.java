package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;
import com.dh.digitalbooking.entity.Country;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaisMapper {
    public PaisDTO paisToPaisDTO(Country country) {
        return new PaisDTO(country.getId(), country.getName());
    }

    public Country paisDTOtoPais(PaisDTO paisDTO){
        Country country = new Country();
        country.setId(paisDTO.id());
        country.setName(paisDTO.nombre());
        return country;
    }

    public Country paisNoIdDTOToPais(PaisNoIdDTO paisNoIdDTO) {
        Country country = new Country();
        country.setName(paisNoIdDTO.nombre());
        return country;
    }

    public List<PaisDTO> listPaisToPaisDTO(List<Country> paises) {
        return paises.stream().map(this::paisToPaisDTO).toList();
    }
}
