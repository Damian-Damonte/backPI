package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.ciudad.CiudadDTO;
import com.dh.digitalbooking.dto.ciudad.CiudadPostDTO;
import com.dh.digitalbooking.dto.ciudad.CityPutDTO;
import com.dh.digitalbooking.service.imp.CiudadServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {
    private final CiudadServiceImp ciudadServiceImp;

    public CiudadController(CiudadServiceImp ciudadServiceImp) {
        this.ciudadServiceImp = ciudadServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<CiudadDTO>> getAll() {
        return ResponseEntity.ok(ciudadServiceImp.allCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ciudadServiceImp.getByIdCiudad(id));
    }

    @PostMapping
    public ResponseEntity<CiudadDTO> saveCiudad(@RequestBody @Valid CiudadPostDTO ciudadPostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadServiceImp.saveCiudad(ciudadPostDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        ciudadServiceImp.deleteCiudad(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<CiudadDTO> updateCiudad(@RequestBody @Valid CityPutDTO cityPutDTO) {
        return ResponseEntity.ok(ciudadServiceImp.updateCiudad(cityPutDTO));
    }
}
