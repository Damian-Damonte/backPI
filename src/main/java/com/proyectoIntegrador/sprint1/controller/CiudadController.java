package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Ciudad;
import com.proyectoIntegrador.sprint1.service.imp.CiudadServiceImp;
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
    public ResponseEntity<List<Ciudad>> getAll() {
        return ResponseEntity.ok(ciudadServiceImp.allCiudad());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ciudad> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ciudadServiceImp.getByIdCiudad(id));
    }

    @PostMapping
    public ResponseEntity<Ciudad> saveCiudad(@RequestBody Ciudad ciudad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ciudadServiceImp.saveCiudad(ciudad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable Long id) {
        ciudadServiceImp.deleteCiudad(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Ciudad> updateCiudad(@RequestBody Ciudad ciudad) {
        return ResponseEntity.ok(ciudadServiceImp.updateCiudad(ciudad));
    }
}
