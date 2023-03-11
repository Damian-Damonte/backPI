package com.dh.digitalbooking.security;

import com.dh.digitalbooking.DTO.AuthenticateRequest;
import com.dh.digitalbooking.DTO.AuthenticationResponse;
import com.dh.digitalbooking.DTO.RegisterRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Rol;
import com.dh.digitalbooking.model.Usuario;
import com.dh.digitalbooking.repository.RoleRepository;
import com.dh.digitalbooking.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        usuarioValidation(request);

        Rol rol = roleRepository.findByNombre("ROLE_USER").orElseThrow(
                () -> new NotFoundException("Role no encontrado"));

        Usuario usuario = new Usuario(
                request.getNombre(),
                request.getApellido(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                rol
        );

        usuarioRepository.save(usuario);

        String jwtToken = jwtService.generateToken(usuario);
        return new AuthenticationResponse(jwtToken);
    };

    public AuthenticationResponse authenticate(AuthenticateRequest requestPayload) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestPayload.getEmail(),
                        requestPayload.getPassword()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(requestPayload.getEmail())
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));

        String jwtToken = jwtService.generateToken(usuario);

        return new AuthenticationResponse(jwtToken);
    }

    private void usuarioValidation(RegisterRequest request) {
        String email = request.getEmail();
        if(usuarioRepository.findByEmail(email).isPresent())
            throw new BadRequestException("El email '" + email + "' ya se encuentra registrado");
    }
}
