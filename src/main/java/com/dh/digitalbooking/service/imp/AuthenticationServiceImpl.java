package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.auth.AuthRequest;
import com.dh.digitalbooking.dto.auth.AuthResponse;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.security.JwtService;
import com.dh.digitalbooking.service.AuthenticationService;
import com.dh.digitalbooking.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationServiceImpl(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public AuthResponse register(UserRequest userRequest) {
        User user = userService.saveUsuario(userRequest);

        String jwtToken = jwtService.generateToken(getClaims(user), user);
        return new AuthResponse(jwtToken);
    };

    public AuthResponse authenticate(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.email(),
                        authRequest.password()
                )
        );
        User user = userService.findByEmail(authRequest.email());
        String jwtToken = jwtService.generateToken(getClaims(user) , user);
        return new AuthResponse(jwtToken);
    }

    private Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("role", user.getRole().name().substring(5));
        return claims;
    }
}
