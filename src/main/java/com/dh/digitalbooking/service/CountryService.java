package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.country.CountryFull;
import com.dh.digitalbooking.dto.country.CountryRequest;
import com.dh.digitalbooking.entity.Country;

import java.util.List;

public interface CountryService {
    List<CountryFull> getAllCountries();
    CountryFull getCountryById(Long id);
    CountryFull saveCountry(CountryRequest countryRequest);
    void deleteCountry(Long id);
    CountryFull updateCountry(Long id, CountryRequest countryRequest);
    Country countryExistsById(Long id);
}
