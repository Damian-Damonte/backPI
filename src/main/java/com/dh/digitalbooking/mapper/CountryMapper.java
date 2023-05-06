package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.pais.CountryFullDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;
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

    public Country countryNoIdDTOToCountry(PaisNoIdDTO paisNoIdDTO) {
        Country country = new Country();
        country.setName(paisNoIdDTO.name());
        return country;
    }

    public List<CountryFullDTO> listCountryToCountryFullDTO(List<Country> countries) {
        return countries.stream().map(this::countryToContryFullDTO).toList();
    }
}
