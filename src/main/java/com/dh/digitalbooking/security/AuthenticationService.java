package com.dh.digitalbooking.security;

import com.dh.digitalbooking.dto.AuthenticateRequest;
import com.dh.digitalbooking.dto.AuthenticationResponse;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.service.UserService;
import com.dh.digitalbooking.service.imp.UserServiceImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserService userService) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public AuthenticationResponse register(UserRequest userRequest) {
        User user = userService.saveUsuario(userRequest);

        String jwtToken = jwtService.generateToken(getClaims(user), user);
        return new AuthenticationResponse(jwtToken);
    };

    public AuthenticationResponse authenticate(AuthenticateRequest requestPayload) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestPayload.getEmail(),
                        requestPayload.getPassword()
                )
        );
        User user = userService.findByEmail(requestPayload.getEmail());
        String jwtToken = jwtService.generateToken(getClaims(user) , user);
        return new AuthenticationResponse(jwtToken);
    }

    private Map<String, Object> getClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("role", user.getRole().getNombre().substring(5));
        return claims;
    }
}
