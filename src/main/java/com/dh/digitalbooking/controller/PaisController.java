package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.dto.pais.PaisNoIdDTO;
import com.dh.digitalbooking.service.imp.PaisServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {
    private final PaisServiceImp paisServiceImp;

    public PaisController(PaisServiceImp paisServiceImp) {
        this.paisServiceImp = paisServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<PaisDTO>> getAll() {
        return ResponseEntity.ok(paisServiceImp.allPais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(paisServiceImp.getByIdPais(id));
    }

    @PostMapping
    public ResponseEntity<PaisDTO> savePais(@RequestBody @Valid PaisNoIdDTO paisNoIdDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paisServiceImp.savePais(paisNoIdDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePais(@PathVariable Long id) {
        paisServiceImp.deletePais(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<PaisDTO> updatePais(@RequestBody @Valid PaisDTO PaisDTO) {
        return ResponseEntity.ok(paisServiceImp.updatePais(PaisDTO));
    }
}
