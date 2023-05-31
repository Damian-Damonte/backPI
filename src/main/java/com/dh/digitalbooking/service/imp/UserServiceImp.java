package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.FavoritoDto;
import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.mapper.UserMapper;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.repository.UserRespository;
import com.dh.digitalbooking.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final ProductoServiceImp productoServiceImp;
    private final UserMapper userMapper;

    public UserServiceImp(UserRespository userRespository, PasswordEncoder passwordEncoder, ProductoServiceImp productoServiceImp, UserMapper userMapper) {
        this.userRespository = userRespository;
        this.passwordEncoder = passwordEncoder;
        this.productoServiceImp = productoServiceImp;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> allUsuario() {
        return userRespository.findAll().stream().map(userMapper::userToUserResponse).toList();
    }

    @Override
    public UserFullDto getByIdUsuario(Long id, UserDetailsDto userDetailsDto) {
        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!id.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }
        return userMapper.userToUserFullDto(existByIdValidation(id));
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
        User user = existByIdValidation(id);
        user.getFavorites().clear();
        userRespository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse updateUsuario(Long id, UserRequest userRequest) {
        User user = existByIdValidation(id);
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
    public void handleFav(FavoritoDto favoritoDto, UserDetailsDto userDetailsDto) {
        Long userId = favoritoDto.getUsuarioId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!userId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del user proporcionado no coincide con el user actualmente autenticado");
        }

        Producto producto = productoServiceImp.existByIdValidation(favoritoDto.getProductoId());
        User user = this.existByIdValidation(userId);

        boolean isFav = user.getFavorites().contains(producto);
        if (isFav) {
            user.removeFav(producto);
        } else {
            user.addFav(producto);
        }
        userRespository.save(user);
    }

    public User existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del usuario");
        return userRespository.findById(id).orElseThrow(() ->
                new NotFoundException("User con id " + id + " no encontrado"));
    }
}
