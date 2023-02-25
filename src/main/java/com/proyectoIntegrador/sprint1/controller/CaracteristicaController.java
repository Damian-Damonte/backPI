package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Caracteristica;
import com.proyectoIntegrador.sprint1.model.Pais;
import com.proyectoIntegrador.sprint1.service.imp.CaracteristicaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicaController {
    private final CaracteristicaServiceImp caracteristicaServiceImp;

    public CaracteristicaController(CaracteristicaServiceImp caracteristicaServiceImp) {
        this.caracteristicaServiceImp = caracteristicaServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Caracteristica>> getAllCaracteristica() {
        return ResponseEntity.ok(caracteristicaServiceImp.getAllCaracteristica());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caracteristica> getByIdCaracteristica(@PathVariable("id") Long id) {
        return ResponseEntity.ok(caracteristicaServiceImp.getByIdCaracteristica(id));
    }

    @PostMapping
    public ResponseEntity<Caracteristica> saveCaracteristica(@RequestBody Caracteristica caracteristica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaServiceImp.saveCaracteristica(caracteristica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        caracteristicaServiceImp.deleteCaracteristica(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Caracteristica> updateCaracteristica(@RequestBody Caracteristica caracteristica) {
        return ResponseEntity.ok(caracteristicaServiceImp.updateCaracteristica(caracteristica));
    }
}
