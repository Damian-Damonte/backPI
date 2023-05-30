package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.dto.amenity.AmenityRequest;
import com.dh.digitalbooking.entity.Amenity;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapperOld {
    public AmenityFullDto amenityToAmenityFullDTO(Amenity amenity){
        return new AmenityFullDto(amenity.getId(), amenity.getName());
    }

    public Amenity amenityFullDTOToAmenity(AmenityFullDto amenityFullDTO) {
        Amenity amenity = new Amenity();
        amenity.setId(amenityFullDTO.id());
        amenity.setName(amenityFullDTO.name());
        return amenity;
    }

    public Amenity amenityOnlyNameDTOToAmenity(AmenityRequest amenityRequest) {
        Amenity amenity = new Amenity();
        amenity.setName(amenityRequest.name());
        return amenity;
    }
}
