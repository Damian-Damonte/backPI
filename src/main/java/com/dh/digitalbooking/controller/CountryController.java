package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.pais.CountryFullDTO;
import com.dh.digitalbooking.dto.pais.CountryNoIdDTO;
import com.dh.digitalbooking.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryFullDTO>> getAll() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryFullDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryFullDTO> saveCountry(@RequestBody @Valid CountryNoIdDTO countryNoIdDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.saveCountry(countryNoIdDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CountryFullDTO> updateCountry(@RequestBody @Valid CountryFullDTO CountryFullDTO) {
        return ResponseEntity.ok(countryService.updateCountry(CountryFullDTO));
    }
}
