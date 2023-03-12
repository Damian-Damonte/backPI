package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.service.imp.ReservaServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaServiceImp reservaServiceImp;

    public ReservaController(ReservaServiceImp reservaServiceImp) {
        this.reservaServiceImp = reservaServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> getAllReserva() {
        return ResponseEntity.ok(reservaServiceImp.allReserva());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getByIdReserva(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservaServiceImp.getByIdReseva(id));
    }

    @PostMapping
    public ResponseEntity<Reserva> saveReserva(@RequestBody @Valid Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServiceImp.saveReserva(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaServiceImp.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaServiceImp.updateReserva(reserva));
    }
}
