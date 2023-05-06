package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.pais.CountryFullDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Country;
import com.dh.digitalbooking.mapper.CountryMapper;
import com.dh.digitalbooking.repository.PaisRepository;
import com.dh.digitalbooking.service.PaisService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaisServiceImp implements PaisService {

    private final PaisRepository paisRepository;
    private final CountryMapper countryMapper;
    private final CiudadServiceImp ciudadServiceImp;

    public PaisServiceImp(PaisRepository paisRepository, CountryMapper countryMapper, @Lazy CiudadServiceImp ciudadServiceImp) {
        this.paisRepository = paisRepository;
        this.countryMapper = countryMapper;
        this.ciudadServiceImp = ciudadServiceImp;
    }

    @Override
    public List<CountryFullDTO> allPais() {
        return countryMapper.listCountryToCountryFullDTO(paisRepository.findAll());
    }

    @Override
    public CountryFullDTO getByIdPais(Long id) {
        return countryMapper.countryToContryFullDTO(existByIdValidation(id));
    }

    @Override
    public CountryFullDTO savePais(PaisNoIdDTO paisNoIdDTO) {
        String nombre = paisNoIdDTO.name();
        if(paisRepository.findByName(nombre).isPresent())
            throw new BadRequestException("Ya existe un pais con el name '" + nombre + "'");
        Country country = countryMapper.countryNoIdDTOToCountry(paisNoIdDTO);

        return countryMapper.countryToContryFullDTO(paisRepository.save(country));
    }

    @Override
    public void deletePais(Long id) {
        Country country = existByIdValidation(id);
        if(ciudadServiceImp.existsCityByCountryId(id))
            throw new BadRequestException("No puede eliminar el pais con id " + id + " ya que hay ciudades registradas en dicho pais");
        paisRepository.deleteById(id);
    }

    @Override
    public CountryFullDTO updatePais(CountryFullDTO countryFullDTO) {
        Long id = countryFullDTO.id();
        String nombre = countryFullDTO.name();
        existByIdValidation(id);

        Country countryByNombre = paisRepository.findByName(nombre).orElse(null);
        if(countryByNombre != null && !(countryByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe un pais con el name '" + nombre + "'");

        Country country = countryMapper.countryFullDTOToCountry(countryFullDTO);
        return countryMapper.countryToContryFullDTO(paisRepository.save(country));
    }

    public Country existByIdValidation(Long id) {
        return paisRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Pais con id " + id + " no encontrado"));
    }
}
