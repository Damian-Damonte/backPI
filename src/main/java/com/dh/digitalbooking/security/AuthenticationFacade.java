package com.dh.digitalbooking.security;

import com.dh.digitalbooking.dto.AuthenticationDto;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public AuthenticationDto getUserInfo(Authentication authentication) {
        if (
                authentication == null
                || !authentication.isAuthenticated()
                || !(authentication.getPrincipal() instanceof Usuario)) {
            throw new BadRequestException("El usuario no está autenticado o no se pudo encontrar la información del usuario autenticado");
        }

        Long userId = ((Usuario) authentication.getPrincipal()).getId();
        String userRol = authentication.getAuthorities().stream().findFirst()
                .map(GrantedAuthority::getAuthority).orElse(null);

//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        userRol = authentication.getAuthorities().stream().findFirst()
//                .map(GrantedAuthority::getAuthority).orElse(null);
        if (userRol == null)
            throw new BadRequestException("No se puedo obtener el rol del usuario");


        System.out.println("-----------------------++++++++++++++************");
        System.out.println(userRol);

        return new AuthenticationDto(userId, userRol);
    }
}
