package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.city.CityFullDTO;
import com.dh.digitalbooking.dto.city.CityPostDTO;
import com.dh.digitalbooking.dto.city.CityPutDTO;
import com.dh.digitalbooking.entity.City;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Country;
import com.dh.digitalbooking.mapper.CityMapper;
import com.dh.digitalbooking.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityService implements com.dh.digitalbooking.service.CityService {
    private final CityRepository cityRepository;
    private final CountryServiceImpl countryServiceImpl;
    private final CityMapper cityMapper;

    public CityService(CityRepository cityRepository, CountryServiceImpl countryServiceImpl, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.countryServiceImpl = countryServiceImpl;
        this.cityMapper = cityMapper;
    }

    @Override
    public List<CityFullDTO> getAllCities() {
        return cityRepository.findAll().stream().map(cityMapper::cityToCityFullDTO).toList();
    }

    @Override
    public CityFullDTO getCityById(Long id) {
        return cityMapper.cityToCityFullDTO(existByIdValidation(id));
    }

    @Override
    public CityFullDTO saveCity(CityPostDTO cityPostDTO) {
        Country country = countryServiceImpl.existByIdValidation(cityPostDTO.countryId());
        City city = cityMapper.cityPostDTOToCity(cityPostDTO);
        city.setPais(country);
        return cityMapper.cityToCityFullDTO(cityRepository.save(city));
    }

//    BORRAR RELACION BIDIRECCIONAL CON PRODUCTOS, crear metodo que pregunta si hay un producto un cierta city
    @Override
    public void deleteCity(Long id) {
        City city = existByIdValidation(id);
        if(!(city.getProducts().isEmpty()))
            throw new BadRequestException("You cannot delete the city with id " + id + " because there are products registered in that city");
        cityRepository.deleteById(id);
    }
//  CREAR METODO GET CIUDAD, se usa el mismo codigo en save y update
    @Override
    public CityFullDTO updateCity(CityPutDTO cityPutDTO) {
        existByIdValidation(cityPutDTO.id());
        Country country = countryServiceImpl.existByIdValidation(cityPutDTO.countryId());
        City city = cityMapper.cityPutDTOToCity(cityPutDTO);
        city.setPais(country);
        return cityMapper.cityToCityFullDTO(cityRepository.save(city));
    }

    @Override
    public boolean existsCityByCountryId(Long id) {
        return cityRepository.existsByCountry_Id(id);
    }

    public City existByIdValidation(Long id) {
        return cityRepository.findById(id).orElseThrow(() ->
                new NotFoundException("City with id  " + id + " not found"));
    }
}
