package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.city.CityPutDTO;
import com.dh.digitalbooking.dto.city.CityFullDTO;
import com.dh.digitalbooking.dto.city.CityPostDTO;
import com.dh.digitalbooking.dto.country.CountryFull;
import com.dh.digitalbooking.entity.City;
import com.dh.digitalbooking.entity.Country;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityFullDTO cityToCityFullDTO(City city) {
        CountryFull countryFull = new CountryFull(city.getCountry().getId(), city.getCountry().getName());
        return new CityFullDTO(city.getId(), city.getName(), countryFull);
    }

    public City cityPostDTOToCity(CityPostDTO cityPostDTO) {
        Country country = new Country();
        country.setId(cityPostDTO.countryId());

        City city = new City();
        city.setName(cityPostDTO.name());
        city.setCountry(country);
        return city;
    }

    public City cityPutDTOToCity(CityPutDTO cityPutDTO) {
        Country country = new Country();
        country.setId(cityPutDTO.countryId());

        City city = new City();
        city.setId(cityPutDTO.id());
        city.setName(cityPutDTO.name());
        city.setCountry(country);
        return city;
    }
}
