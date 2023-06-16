package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.auth.AuthRequest;
import com.dh.digitalbooking.dto.auth.AuthResponse;
import com.dh.digitalbooking.dto.user.UserRequest;

public interface AuthenticationService {
    AuthResponse register(UserRequest userRequest);
    AuthResponse authenticate(AuthRequest authRequest);
}
