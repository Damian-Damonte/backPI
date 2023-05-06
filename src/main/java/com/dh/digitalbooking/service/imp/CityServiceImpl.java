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
import com.dh.digitalbooking.service.CityService;
import com.dh.digitalbooking.service.CountryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryService countryService;
    private final CityMapper cityMapper;
    private final ProductoServiceImp productoServiceImp;

    public CityServiceImpl(CityRepository cityRepository, CountryService countryService, CityMapper cityMapper, @Lazy ProductoServiceImp productoServiceImp) {
        this.cityRepository = cityRepository;
        this.countryService = countryService;
        this.cityMapper = cityMapper;
        this.productoServiceImp = productoServiceImp;
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
        Country country = countryService.existByIdValidation(cityPostDTO.countryId());
        City city = cityMapper.cityPostDTOToCity(cityPostDTO);
        city.setCountry(country);
        return cityMapper.cityToCityFullDTO(cityRepository.save(city));
    }

    @Override
    public void deleteCity(Long id) {
        existByIdValidation(id);
        if(productoServiceImp.existByCityId(id))
            throw new BadRequestException("You cannot delete the city with id " + id + " because there are products registered in that city");
        cityRepository.deleteById(id);
    }

    @Override
    public CityFullDTO updateCity(CityPutDTO cityPutDTO) {
        existByIdValidation(cityPutDTO.id());
        Country country = countryService.existByIdValidation(cityPutDTO.countryId());
        City city = cityMapper.cityPutDTOToCity(cityPutDTO);
        city.setCountry(country);

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
