package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.model.TipoPolitica;
import com.dh.digitalbooking.service.imp.TipoPoliticaServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tipos-politicas")
public class TipoPoliticaController {
    private final TipoPoliticaServiceImp tipoPoliticaServiceImp;

    public TipoPoliticaController(TipoPoliticaServiceImp tipoPoliticaServiceImp) {
        this.tipoPoliticaServiceImp = tipoPoliticaServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<TipoPolitica>> getAll() {
        return ResponseEntity.ok(tipoPoliticaServiceImp.allTipoPolitica());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPolitica> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(tipoPoliticaServiceImp.getByIdTipoPolitica(id));
    }

    @PostMapping
    public ResponseEntity<TipoPolitica> saveTipoPolitica(@RequestBody TipoPolitica tipoPolitica) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                tipoPoliticaServiceImp.saveTipoPolitica(tipoPolitica));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPolitica(@PathVariable Long id) {
        tipoPoliticaServiceImp.deleteTipoPolitica(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<TipoPolitica> updateTipoPolitica(@RequestBody TipoPolitica tipoPolitica) {
        return ResponseEntity.ok(tipoPoliticaServiceImp.updateTipoPolitica(tipoPolitica));
    }
}
