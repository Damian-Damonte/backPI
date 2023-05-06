package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.city.CityFullDTO;
import com.dh.digitalbooking.dto.city.CityPostDTO;
import com.dh.digitalbooking.dto.city.CityPutDTO;
import com.dh.digitalbooking.service.imp.CityServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityServiceImpl cityServiceImpl;

    public CityController(CityServiceImpl cityServiceImpl) {
        this.cityServiceImpl = cityServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<CityFullDTO>> getAllCities() {
        return ResponseEntity.ok(cityServiceImpl.getAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityFullDTO> getCityById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(cityServiceImpl.getCityById(id));
    }

    @PostMapping
    public ResponseEntity<CityFullDTO> saveCity(@RequestBody @Valid CityPostDTO cityPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityServiceImpl.saveCity(cityPostDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityServiceImpl.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CityFullDTO> updateCity(@RequestBody @Valid CityPutDTO cityPutDTO) {
        return ResponseEntity.ok(cityServiceImpl.updateCity(cityPutDTO));
    }
}
