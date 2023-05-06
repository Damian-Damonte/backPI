package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.amenity.AmenityFullDTO;
import com.dh.digitalbooking.dto.amenity.AmenityOnlyNameDTO;
import com.dh.digitalbooking.entity.Amenity;

import java.util.List;

public interface AmenityService {
    List<AmenityFullDTO> getAllAmenities();
    AmenityFullDTO getAmenityById(Long id);
    AmenityFullDTO saveAmenity(AmenityOnlyNameDTO amenityOnlyNameDTO);
    void deleteAmenity(Long id);
    AmenityFullDTO updateAmenity(AmenityFullDTO amenityFullDTO);
    Amenity existByIdValidation(Long id);
}
