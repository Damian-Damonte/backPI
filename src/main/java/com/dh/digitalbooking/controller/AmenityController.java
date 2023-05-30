package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.amenity.AmenityFullDto;
import com.dh.digitalbooking.dto.amenity.AmenityRequest;
import com.dh.digitalbooking.service.AmenityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<AmenityFullDto>> getAllAmenities() {
        return ResponseEntity.ok(amenityService.getAllAmenities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AmenityFullDto> getAmenityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(amenityService.getAmenityById(id));
    }

    @PostMapping
    public ResponseEntity<AmenityFullDto> saveAmenity(@RequestBody @Valid AmenityRequest amenityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(amenityService.saveAmenity(amenityRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "The amenity will be removed from all products")
    public ResponseEntity<Void> deleteAmenity(@PathVariable Long id) {
        amenityService.deleteAmenity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AmenityFullDto> updateAmenity(@PathVariable Long id,
                                                        @RequestBody @Valid AmenityRequest amenityRequest) {
        return ResponseEntity.ok(amenityService.updateAmenity(id, amenityRequest));
    }
}
