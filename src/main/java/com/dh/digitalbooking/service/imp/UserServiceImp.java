package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.mapper.UserMapper;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.repository.UserRespository;
import com.dh.digitalbooking.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final ProductServiceImp productoServiceImp;
    private final UserMapper userMapper;
    private final AuthenticationUserServiceImpl authenticationUserServiceImpl;

    public UserServiceImp(UserRespository userRespository, PasswordEncoder passwordEncoder, ProductServiceImp productoServiceImp, UserMapper userMapper, AuthenticationUserServiceImpl authenticationUserServiceImpl) {
        this.userRespository = userRespository;
        this.passwordEncoder = passwordEncoder;
        this.productoServiceImp = productoServiceImp;
        this.userMapper = userMapper;
        this.authenticationUserServiceImpl = authenticationUserServiceImpl;
    }

    @Override
    public List<UserResponse> allUsuario() {
        return userRespository.findAll().stream().map(userMapper::userToUserResponse).toList();
    }

    @Override
    public UserFullDto getByIdUsuario(Long id, Authentication authentication) {
        UserDetailsSlim userDetails = authenticationUserServiceImpl.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!id.equals(userDetails.id()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }

        return userMapper.userToUserFullDto(existById(id));
    }

    @Override
    public User findByEmail(String email) {
        return userRespository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User con email " + email + " no encontrado"));
    }

    @Override
    @Transactional
    public User saveUsuario(UserRequest userRequest) {
        String email = userRequest.email();
        if (userRespository.findByEmail(email).isPresent())
            throw new BadRequestException("El email '" + email + "' ya se encuentra registrado");

        User user = User.builder()
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .role(User.Role.ROLE_USER)
                .build();

        return userRespository.save(user);
    }

    @Override
    @Transactional
    public void deleteUsuario(Long id) {
        User user = existById(id);
        user.getFavorites().clear();
        userRespository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse updateUsuario(Long id, UserRequest userRequest) {
        User user = existById(id);
        String email = userRequest.email();
        User userByEmail = userRespository.findByEmail(email).orElse(null);
        if (userByEmail != null && !(userByEmail.getId().equals(id)))
            throw new BadRequestException("El email '" + email + "' ya se encuentra registrado");
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        return userMapper.userToUserResponse(user);
    }

    @Override
    public void handleFav(Long productId, Authentication authentication) {
        User user = existById(authenticationUserServiceImpl.getUserDetailsFromAuthentication(authentication).id());
        Product product = productoServiceImp.existByIdValidation(productId);
        boolean isFav = user.getFavorites().contains(product);
        if (isFav) user.removeFav(product);
         else user.addFav(product);
        userRespository.save(user);
    }

    public User existById(Long id) {
        return userRespository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found"));
    }
}
