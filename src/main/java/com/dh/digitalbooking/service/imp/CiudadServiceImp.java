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
    private final CountryServiceImpl countryServiceImpl;
    private final CiudadMapper ciudadMapper;

    public CiudadServiceImp(CiudadRepository ciudadRepository, CountryServiceImpl countryServiceImpl, CiudadMapper ciudadMapper) {
        this.ciudadRepository = ciudadRepository;
        this.countryServiceImpl = countryServiceImpl;
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
        Country country = countryServiceImpl.existByIdValidation(ciudadPostDTO.paisId());
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
//  CREAR METODO GET CIUDAD, se usa el mismo codigo en save y update
    @Override
    public CiudadDTO updateCiudad(CityPutDTO cityPutDTO) {
        existByIdValidation(cityPutDTO.id());
        Country country = countryServiceImpl.existByIdValidation(cityPutDTO.paisId());
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
