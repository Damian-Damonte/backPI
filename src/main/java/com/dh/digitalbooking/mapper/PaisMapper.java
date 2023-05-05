package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.entity.Pais;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaisMapper {
    public PaisDTO paisToPaisDTO(Pais pais) {
        return new PaisDTO(pais.getId(), pais.getNombre());
    }

    public Pais paisDTOtoPais(PaisDTO paisDTO){
        Pais pais = new Pais();
        pais.setId(paisDTO.id());
        pais.setNombre(paisDTO.nombre());
        return pais;
    }

    public List<PaisDTO> listPaisToPaisDTO(List<Pais> paises) {
        return paises.stream().map(this::paisToPaisDTO).toList();
    }
}
