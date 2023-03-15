package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.imp.ReservaServiceImp;
import com.dh.digitalbooking.service.imp.UsuarioServiceImp;
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
    private final UsuarioServiceImp usuarioServiceImp;

    public ReservaController(ReservaServiceImp reservaServiceImp, AuthenticationFacade authenticationFacade, UsuarioServiceImp usuarioServiceImp) {
        this.reservaServiceImp = reservaServiceImp;
        this.authenticationFacade = authenticationFacade;
        this.usuarioServiceImp = usuarioServiceImp;
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
        UserDetailsDto userDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaServiceImp.saveReserva(reserva, userDto));
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
