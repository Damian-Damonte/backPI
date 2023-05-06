package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.amenity.AmenityFullDTO;
import com.dh.digitalbooking.dto.amenity.AmenityOnlyNameDTO;
import com.dh.digitalbooking.entity.Amenity;
import org.springframework.stereotype.Component;

@Component
public class AmenityMapper {
    public AmenityFullDTO amenityToAmenityFullDTO(Amenity amenity){
        return new AmenityFullDTO(amenity.getId(), amenity.getName());
    }

    public Amenity amenityFullDTOToAmenity(AmenityFullDTO amenityFullDTO) {
        Amenity amenity = new Amenity();
        amenity.setId(amenityFullDTO.id());
        amenity.setName(amenityFullDTO.name());
        return amenity;
    }

    public Amenity amenityOnlyNameDTOToAmenity(AmenityOnlyNameDTO amenityOnlyNameDTO) {
        Amenity amenity = new Amenity();
        amenity.setName(amenityOnlyNameDTO.name());
        return amenity;
    }
}
