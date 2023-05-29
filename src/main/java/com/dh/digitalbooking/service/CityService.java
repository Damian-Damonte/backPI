package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.city.CityFullDto;
import com.dh.digitalbooking.dto.city.CityRequest;
import com.dh.digitalbooking.entity.City;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CityService {
    List<CityFullDto> getAllCities();
    CityFullDto getCityById(Long id);
    CityFullDto saveCity(CityRequest cityRequest);
    void deleteCity(Long id);
    CityFullDto updateCity(Long id, CityRequest cityRequest);
    boolean existsCityByCountryId(Long id);
    City existByIdValidation(Long id);
}
