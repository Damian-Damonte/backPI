package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.ReservaDto;
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
    public ResponseEntity<List<ReservaDto>> getAllReserva() {
        return ResponseEntity.ok(reservaServiceImp.allReserva());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> getByIdReserva(@PathVariable("id") Long id) {
        return ResponseEntity.ok(reservaServiceImp.getByIdReseva(id));
    }

    @PostMapping
    public ResponseEntity<ReservaDto> saveReserva(@RequestBody @Valid ReservaDto reservaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServiceImp.saveReserva(reservaDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaServiceImp.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ReservaDto> updateReserva(@RequestBody ReservaDto reservaDto) {
        return ResponseEntity.ok(reservaServiceImp.updateReserva(reservaDto));
    }
}
