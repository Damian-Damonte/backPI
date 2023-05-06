package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.amenity.AmenityFullDTO;
import com.dh.digitalbooking.dto.amenity.AmenityOnlyNameDTO;
import com.dh.digitalbooking.entity.Amenity;
import com.dh.digitalbooking.service.imp.AmenityServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {
    private final AmenityServiceImpl amenityServiceImpl;

    public AmenityController(AmenityServiceImpl amenityServiceImpl) {
        this.amenityServiceImpl = amenityServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<AmenityFullDTO>> getAllAmenities() {
        return ResponseEntity.ok(amenityServiceImpl.getAllAmenities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityFullDTO> getAmenityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(amenityServiceImpl.getAmenityById(id));
    }

    @PostMapping
    public ResponseEntity<AmenityFullDTO> saveAmenity(@RequestBody @Valid AmenityOnlyNameDTO amenityOnlyNameDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityServiceImpl.saveAmenity(amenityOnlyNameDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "The amenity will be removed from all products")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityServiceImpl.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<AmenityFullDTO> updateAmenity(@RequestBody @Valid AmenityFullDTO amenityFullDTO) {
        return ResponseEntity.ok(amenityServiceImpl.updateAmenity(amenityFullDTO));
    }
}
