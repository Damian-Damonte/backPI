package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.DTO.AuthenticateRequest;
import com.dh.digitalbooking.DTO.AuthenticationResponse;
import com.dh.digitalbooking.DTO.RegisterRequest;
import com.dh.digitalbooking.security.AuthenticationService;
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
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(registerRequest));
    }

    @GetMapping("/autenticacion")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest authenticateRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authenticateRequest));
    }
}
