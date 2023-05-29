package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.city.CityFullDto;
import com.dh.digitalbooking.entity.City;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CityMapper {
    CityFullDto cityToCityFullDto(City city);
}
