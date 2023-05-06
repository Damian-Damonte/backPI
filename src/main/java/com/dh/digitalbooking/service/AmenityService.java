package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Amenity;

import java.util.List;

public interface AmenityService {
    List<Amenity> getAllAmenities();
    Amenity getAmenityById(Long id);
    Amenity saveAmenity(Amenity amenity);
    void deleteAmenity(Long id);
    Amenity updateAmenity(Amenity amenity);
}
