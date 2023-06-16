package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import org.springframework.security.core.Authentication;

public interface AuthenticationUserService {
    UserDetailsSlim getUserDetailsFromAuthentication(Authentication authentication);
}
