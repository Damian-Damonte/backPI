package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.pais.CountryFullDTO;
import com.dh.digitalbooking.dto.pais.CountryNoIdDTO;

import java.util.List;

public interface CountryService {
    List<CountryFullDTO> getAllCountries();
    CountryFullDTO getCountryById(Long id);
    CountryFullDTO saveCountry(CountryNoIdDTO countryNoIdDTO);
    void deleteCountry(Long id);
    CountryFullDTO updateCountry(CountryFullDTO countryFullDTO);
}
