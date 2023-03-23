package com.dh.digitalbooking.security;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public UserDetailsDto getUserInfo(Authentication authentication) {
        if (
                authentication == null
                        || !authentication.isAuthenticated()
                        || !(authentication.getPrincipal() instanceof Usuario)) {
            throw new BadRequestException("El usuario no está autenticado o no se pudo obtener la información del usuario autenticado");
        }

        Long userId = ((Usuario) authentication.getPrincipal()).getId();
        String userRol = authentication.getAuthorities().stream().findFirst()
                .map(GrantedAuthority::getAuthority).orElseThrow(() ->
                        new BadRequestException("No se puedo obtener el rol del usuario")
                );
        return new UserDetailsDto(userId, userRol);
    }
}
