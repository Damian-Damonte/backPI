package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.auth.AuthRequest;
import com.dh.digitalbooking.dto.auth.AuthResponse;
import com.dh.digitalbooking.dto.user.UserRequest;
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
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.register(userRequest));
    }

    @PostMapping("/autenticacion")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(authRequest));
    }
}
