package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.country.CountryFullDTO;
import com.dh.digitalbooking.dto.country.CountryNoIdDTO;
import com.dh.digitalbooking.entity.Country;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CountryMapper {
    public CountryFullDTO countryToContryFullDTO(Country country) {
        return new CountryFullDTO(country.getId(), country.getName());
    }

    public Country countryFullDTOToCountry(CountryFullDTO countryFullDTO){
        Country country = new Country();
        country.setId(countryFullDTO.id());
        country.setName(countryFullDTO.name());
        return country;
    }

    public Country countryNoIdDTOToCountry(CountryNoIdDTO countryNoIdDTO) {
        Country country = new Country();
        country.setName(countryNoIdDTO.name());
        return country;
    }

    public List<CountryFullDTO> listCountryToCountryFullDTO(List<Country> countries) {
        return countries.stream().map(this::countryToContryFullDTO).toList();
    }
}
