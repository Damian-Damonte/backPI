package com.dh.digitalbooking.security;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public Long getUserId(Authentication authentication) {
        if (
                authentication == null
                || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof Usuario)) {
            throw new BadRequestException("El usuario no está autenticado o no se pudo encontrar la información del usuario autenticado");
        }
        return ((Usuario) authentication.getPrincipal()).getId();
    }
}
