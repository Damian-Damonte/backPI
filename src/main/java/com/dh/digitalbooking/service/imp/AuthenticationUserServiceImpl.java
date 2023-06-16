package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.service.AuthenticationUserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUserServiceImpl implements AuthenticationUserService {
    public UserDetailsSlim getUserDetailsFromAuthentication(Authentication authentication) {
        if (
                authentication == null
                        || !authentication.isAuthenticated()
                        || !(authentication.getPrincipal() instanceof User user)) {
            throw new BadRequestException("User is not authenticated or the authentication information couldn't be retrieved");
        }

        return new UserDetailsSlim(user.getId(), user.getRole().name());
    }
}
