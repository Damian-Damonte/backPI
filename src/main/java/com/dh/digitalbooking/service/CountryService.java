package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.country.CountryFullDto;
import com.dh.digitalbooking.dto.country.CountryRequest;
import com.dh.digitalbooking.entity.Country;

import java.util.List;

public interface CountryService {
    List<CountryFullDto> getAllCountries();
    CountryFullDto getCountryById(Long id);
    CountryFullDto saveCountry(CountryRequest countryRequest);
    void deleteCountry(Long id);
    CountryFullDto updateCountry(Long id, CountryRequest countryRequest);
    Country countryExistsById(Long id);
}
