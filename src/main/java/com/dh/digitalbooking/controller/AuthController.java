package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.AuthenticateRequest;
import com.dh.digitalbooking.dto.AuthenticationResponse;
import com.dh.digitalbooking.dto.UsuarioRequestDto;
import com.dh.digitalbooking.security.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registro")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UsuarioRequestDto usuarioRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(usuarioRequestDto));
    }

    @PostMapping("/autenticacion")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequest));
    }
}
