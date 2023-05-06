package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.amenity.AmenityFullDTO;
import com.dh.digitalbooking.dto.amenity.AmenityOnlyNameDTO;
import com.dh.digitalbooking.service.AmenityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {
    private final AmenityService amenityService;

    public AmenityController(AmenityService amenityService) {
        this.amenityService = amenityService;
    }

    @GetMapping
    public ResponseEntity<List<AmenityFullDTO>> getAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityFullDTO> getAmenityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(amenityService.getAmenityById(id));
    }

    @PostMapping
    public ResponseEntity<AmenityFullDTO> saveAmenity(@RequestBody @Valid AmenityOnlyNameDTO amenityOnlyNameDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityService.saveAmenity(amenityOnlyNameDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "The amenity will be removed from all products")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<AmenityFullDTO> updateAmenity(@RequestBody @Valid AmenityFullDTO amenityFullDTO) {
        return ResponseEntity.ok(amenityService.updateAmenity(amenityFullDTO));
    }
}
