package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.country.CountryFull;
import com.dh.digitalbooking.dto.country.CountryRequest;
import com.dh.digitalbooking.service.CountryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/countries")
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryFull>> getAll() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryFull> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryFull> saveCountry(@RequestBody @Valid CountryRequest countryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.saveCountry(countryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryFull> updateCountry(@PathVariable Long id,
                                                     @RequestBody @Valid CountryRequest countryRequest) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryRequest));
    }
}
