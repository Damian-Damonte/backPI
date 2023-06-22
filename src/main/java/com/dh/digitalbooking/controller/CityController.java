package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.city.CityFullDto;
import com.dh.digitalbooking.dto.city.CityRequest;
import com.dh.digitalbooking.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<CityFullDto>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityFullDto> getCityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cityService.getCityById(id));
    }

    @PostMapping
    public ResponseEntity<CityFullDto> saveCity(@RequestBody @Valid CityRequest cityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.saveCity(cityRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "You cannot delete a city if a product is registered in it.")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityFullDto> updateCity(@PathVariable Long id,
                                                  @RequestBody @Valid CityRequest cityRequest) {
        return ResponseEntity.ok(cityService.updateCity(id, cityRequest));
    }
}
