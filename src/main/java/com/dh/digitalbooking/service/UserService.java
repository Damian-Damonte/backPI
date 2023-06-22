package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.entity.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserFullDto getUserById(Long id, Authentication authentication);
    User getUserByEmail(String email);
    User saveUser(UserRequest userRequest);
    void deleteUser(Long id, Authentication authentication);
    UserResponse updateUser(Long id, UserRequest userRequest, Authentication authentication);
    User existById(Long id);
    void handleFav(Long productId, Authentication authentication);
}
