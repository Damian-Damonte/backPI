package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.model.Puntuacion;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.imp.PuntuacionServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntuaciones")
public class PuntuacionController {
    private final PuntuacionServiceImp puntuacionServiceImp;
    private final AuthenticationFacade authenticationFacade;

    public PuntuacionController(PuntuacionServiceImp puntuacionServiceImp, AuthenticationFacade authenticationFacade) {
        this.puntuacionServiceImp = puntuacionServiceImp;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public ResponseEntity<List<Puntuacion>> getAll() {
        return ResponseEntity.ok(puntuacionServiceImp.allPuntuacion());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Puntuacion> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(puntuacionServiceImp.getByIdPuntuacion(id));
    }

    @PostMapping
    public ResponseEntity<Puntuacion> savePuntuacion(
            @RequestBody @Valid Puntuacion puntuacion,
            Authentication authentication
            ) {
        UserDetailsDto userDetailsDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                puntuacionServiceImp.savePuntuacion(puntuacion, userDetailsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePuntuacion(@PathVariable @Valid Long id) {
        puntuacionServiceImp.deletePuntuacion(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Puntuacion> updateCiudad(@RequestBody @Valid  Puntuacion puntuacion) {
        return ResponseEntity.ok(puntuacionServiceImp.updatePuntuacion(puntuacion));
    }
}
