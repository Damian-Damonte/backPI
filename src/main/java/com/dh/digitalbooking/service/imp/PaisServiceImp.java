package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Pais;
import com.dh.digitalbooking.mapper.PaisMapper;
import com.dh.digitalbooking.repository.PaisRepository;
import com.dh.digitalbooking.service.PaisService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaisServiceImp implements PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;
    private final CiudadServiceImp ciudadServiceImp;

    public PaisServiceImp(PaisRepository paisRepository, PaisMapper paisMapper, @Lazy CiudadServiceImp ciudadServiceImp) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
        this.ciudadServiceImp = ciudadServiceImp;
    }

    @Override
    public List<PaisDTO> allPais() {
        return paisMapper.listPaisToPaisDTO(paisRepository.findAll());
    }

    @Override
    public PaisDTO getByIdPais(Long id) {
        return paisMapper.paisToPaisDTO(existByIdValidation(id));
    }

    @Override
    public PaisDTO savePais(PaisNoIdDTO paisNoIdDTO) {
        String nombre = paisNoIdDTO.nombre();
        if(paisRepository.findByNombre(nombre).isPresent())
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");
        Pais pais = paisMapper.paisNoIdDTOToPais(paisNoIdDTO);

        return paisMapper.paisToPaisDTO(paisRepository.save(pais));
    }

    @Override
    public void deletePais(Long id) {
        Pais pais = existByIdValidation(id);
        if(ciudadServiceImp.existsCityByCountryId(id))
            throw new BadRequestException("No puede eliminar el pais con id " + id + " ya que hay ciudades registradas en dicho pais");
        paisRepository.deleteById(id);
    }

    @Override
    public PaisDTO updatePais(PaisDTO paisDTO) {
        Long id = paisDTO.id();
        String nombre = paisDTO.nombre();
        existByIdValidation(id);

        Pais paisByNombre = paisRepository.findByNombre(nombre).orElse(null);
        if(paisByNombre != null && !(paisByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");

        Pais pais = paisMapper.paisDTOtoPais(paisDTO);
        return paisMapper.paisToPaisDTO(paisRepository.save(pais));
    }

    public Pais existByIdValidation(Long id) {
        return paisRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Pais con id " + id + " no encontrado"));
    }
}
