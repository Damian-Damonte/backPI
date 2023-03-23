package com.dh.digitalbooking.security;

import com.dh.digitalbooking.dto.AuthenticateRequest;
import com.dh.digitalbooking.dto.AuthenticationResponse;
import com.dh.digitalbooking.dto.UsuarioRequestDto;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Rol;
import com.dh.digitalbooking.model.Usuario;
import com.dh.digitalbooking.repository.RoleRepository;
import com.dh.digitalbooking.repository.UsuarioRepository;
import com.dh.digitalbooking.service.imp.UsuarioServiceImp;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UsuarioServiceImp usuarioServiceImp;

    public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UsuarioServiceImp usuarioServiceImp) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    public AuthenticationResponse register(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = usuarioServiceImp.saveUsuario(usuarioRequestDto);

        String jwtToken = jwtService.generateToken(getClaims(usuario), usuario);
        return new AuthenticationResponse(jwtToken);
    };

    public AuthenticationResponse authenticate(AuthenticateRequest requestPayload) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestPayload.getEmail(),
                        requestPayload.getPassword()
                )
        );
        Usuario usuario = usuarioServiceImp.findByEmail(requestPayload.getEmail());
        String jwtToken = jwtService.generateToken(getClaims(usuario) ,usuario);
        return new AuthenticationResponse(jwtToken);
    }

    private Map<String, Object> getClaims(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", usuario.getId());
        claims.put("nombre", usuario.getNombre());
        claims.put("apellido", usuario.getApellido());
        return claims;
    }
}
