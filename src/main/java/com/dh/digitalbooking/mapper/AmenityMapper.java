package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.entity.Amenity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AmenityMapper {
    AmenityFullDto amenityToAmenityFullDto(Amenity amenity);
}
