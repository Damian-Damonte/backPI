package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.country.CountryFullDTO;
import com.dh.digitalbooking.dto.country.CountryNoIdDTO;

import java.util.List;

public interface CountryService {
    List<CountryFullDTO> getAllCountries();
    CountryFullDTO getCountryById(Long id);
    CountryFullDTO saveCountry(CountryNoIdDTO countryNoIdDTO);
    void deleteCountry(Long id);
    CountryFullDTO updateCountry(CountryFullDTO countryFullDTO);
}
