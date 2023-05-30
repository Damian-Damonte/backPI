package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.dto.amenity.AmenityRequest;
import com.dh.digitalbooking.entity.Amenity;

import java.util.List;

public interface AmenityService {
    List<AmenityFullDto> getAllAmenities();
    AmenityFullDto getAmenityById(Long id);
    AmenityFullDto saveAmenity(AmenityRequest amenityRequest);
    void deleteAmenity(Long id);
    AmenityFullDto updateAmenity(Long id, AmenityRequest amenityRequest);
    Amenity existByIdValidation(Long id);
}
