package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.entity.User;

import java.util.List;

public interface UserService {
    List<UserResponse> allUsuario();
    UserFullDto getByIdUsuario(Long id, UserDetailsDto userDetailsDto);
    User findByEmail(String email);
    User saveUsuario(UserRequest userRequest);
    void deleteUsuario(Long id);
    UserResponse updateUsuario(Long id, UserRequest userRequest);
    void handleFav(Long productId, UserDetailsDto userDetailsDto);
}
