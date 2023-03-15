package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.imp.ReservaServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    private final ReservaServiceImp reservaServiceImp;
    private final AuthenticationFacade authenticationFacade;

    public ReservaController(ReservaServiceImp reservaServiceImp, AuthenticationFacade authenticationFacade) {
        this.reservaServiceImp = reservaServiceImp;
        this.authenticationFacade = authenticationFacade;
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
    public ResponseEntity<Reserva> saveReserva(
            @RequestBody @Valid Reserva reserva,
            Authentication authentication) {
        Long userId = authenticationFacade.getUserId(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServiceImp.saveReserva(reserva, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        reservaServiceImp.deleteReserva(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Reserva> updateReserva(@RequestBody @Valid Reserva reserva) {
        return ResponseEntity.ok(reservaServiceImp.updateReserva(reserva));
    }
}
