package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.country.CountryFullDto;
import com.dh.digitalbooking.entity.Country;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CountryMapper {
    CountryFullDto countryToCountryFull(Country country);
}
