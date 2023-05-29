package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.country.CountryFull;
import com.dh.digitalbooking.entity.Country;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CountryMapper {
    CountryFull countryToCountryFull(Country country);
}
