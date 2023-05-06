package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ciudad.CiudadDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadPostDTO;
import com.dh.digitalbooking.dto.ciudad.CityPutDTO;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Ciudad;
import com.dh.digitalbooking.entity.Country;
import com.dh.digitalbooking.mapper.CiudadMapper;
import com.dh.digitalbooking.repository.CiudadRepository;
import com.dh.digitalbooking.service.CiudadService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CiudadServiceImp implements CiudadService {
    private final CiudadRepository ciudadRepository;
    private final PaisServiceImp paisServiceImp;
    private final CiudadMapper ciudadMapper;

    public CiudadServiceImp(CiudadRepository ciudadRepository, PaisServiceImp paisServiceImp, CiudadMapper ciudadMapper) {
        this.ciudadRepository = ciudadRepository;
        this.paisServiceImp = paisServiceImp;
        this.ciudadMapper = ciudadMapper;
    }

    @Override
    public List<CiudadDTO> allCiudad() {
        return ciudadRepository.findAll().stream().map(ciudadMapper::ciudadToCiudadDTO).toList();
    }

    @Override
    public CiudadDTO getByIdCiudad(Long id) {
        return ciudadMapper.ciudadToCiudadDTO(existByIdValidation(id));
    }

    @Override
    public CiudadDTO saveCiudad(CiudadPostDTO ciudadPostDTO) {
        Country country = paisServiceImp.existByIdValidation(ciudadPostDTO.paisId());
        Ciudad ciudad = ciudadMapper.ciudadPostDTOToCiudad(ciudadPostDTO);
        ciudad.setPais(country);
        return ciudadMapper.ciudadToCiudadDTO(ciudadRepository.save(ciudad));
    }

    @Override
    public void deleteCiudad(Long id) {
        Ciudad ciudad = existByIdValidation(id);
        if(!(ciudad.getProductos().isEmpty()))
            throw new BadRequestException("No puede eliminar la ciudad con id " + id + " ya que hay productos que en dicha ciudad");
        ciudadRepository.deleteById(id);
    }

    @Override
    public CiudadDTO updateCiudad(CityPutDTO cityPutDTO) {
        existByIdValidation(cityPutDTO.id());
        Country country = paisServiceImp.existByIdValidation(cityPutDTO.paisId());
        Ciudad ciudad = ciudadMapper.cityPutDTOToCity(cityPutDTO);
        ciudad.setPais(country);
        return ciudadMapper.ciudadToCiudadDTO(ciudadRepository.save(ciudad));
    }

    @Override
    public boolean existsCityByCountryId(Long id) {
        return ciudadRepository.existsByPaisId(id) > 0;
    }

    public Ciudad existByIdValidation(Long id) {
        return ciudadRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Ciudad con id " + id + " no encontrada"));
    }
}
