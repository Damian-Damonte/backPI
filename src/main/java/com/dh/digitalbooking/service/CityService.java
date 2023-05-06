package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.city.CityPutDTO;
import com.dh.digitalbooking.dto.city.CityFullDTO;
import com.dh.digitalbooking.dto.city.CityPostDTO;
import com.dh.digitalbooking.entity.City;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityService {
    List<CityFullDTO> getAllCities();
    CityFullDTO getCityById(Long id);
    CityFullDTO saveCity(CityPostDTO cityPostDTO);
    void deleteCity(Long id);
    CityFullDTO updateCity(CityPutDTO cityPutDTO);
    boolean existsCityByCountryId(Long id);
    City existByIdValidation(Long id);
}
