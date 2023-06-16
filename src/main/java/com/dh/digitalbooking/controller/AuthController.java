package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.auth.AuthRequest;
import com.dh.digitalbooking.dto.auth.AuthResponse;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.service.imp.AuthenticationServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationServiceImpl authenticationServiceImpl;

    public AuthController(AuthenticationServiceImpl authenticationServiceImpl) {
        this.authenticationServiceImpl = authenticationServiceImpl;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationServiceImpl.register(userRequest));
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody @Valid AuthRequest authRequest) {
        return ResponseEntity.ok(authenticationServiceImpl.authenticate(authRequest));
    }
}
