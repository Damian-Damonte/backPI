package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.model.Caracteristica;
import com.dh.digitalbooking.service.imp.CaracteristicaServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/caracteristicas")
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
    public ResponseEntity<Caracteristica> saveCaracteristica(@RequestBody @Valid Caracteristica caracteristica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaServiceImp.saveCaracteristica(caracteristica));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "La característica será eliminada de todos los productos.")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        caracteristicaServiceImp.deleteCaracteristica(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Caracteristica> updateCaracteristica(@RequestBody @Valid Caracteristica caracteristica) {
        return ResponseEntity.ok(caracteristicaServiceImp.updateCaracteristica(caracteristica));
    }
}
