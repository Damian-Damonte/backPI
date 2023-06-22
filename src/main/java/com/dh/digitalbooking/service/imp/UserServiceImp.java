package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.entity.Rating;
import com.dh.digitalbooking.exception.ForbiddenException;
import com.dh.digitalbooking.mapper.UserMapper;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.repository.UserRespository;
import com.dh.digitalbooking.service.AuthenticationUserService;
import com.dh.digitalbooking.service.ProductService;
import com.dh.digitalbooking.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productoService;
    private final UserMapper userMapper;
    private final AuthenticationUserService authenticationUserService;

    public UserServiceImp(UserRespository userRespository, PasswordEncoder passwordEncoder, @Lazy ProductServiceImp productoService, UserMapper userMapper, AuthenticationUserServiceImpl authenticationUserService) {
        this.userRespository = userRespository;
        this.passwordEncoder = passwordEncoder;
        this.productoService = productoService;
        this.userMapper = userMapper;
        this.authenticationUserService = authenticationUserService;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRespository.findAll().stream().map(userMapper::userToUserResponse).toList();
    }

    @Override
    public UserFullDto getUserById(Long id, Authentication authentication) {
        UserDetailsSlim userDetails = authenticationUserService.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!id.equals(userDetails.id()))
                throw new ForbiddenException(" You do not have permission to access another user's account information");
        }

        return userMapper.userToUserFullDto(existById(id));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRespository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    @Override
    @Transactional
    public User saveUser(UserRequest userRequest) {
        String email = userRequest.email();
        if (userRespository.findByEmail(email).isPresent())
            throw new BadRequestException("The email '" + email + "' is already registered");

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
    public void deleteUser(Long id, Authentication authentication) {
        User user = existById(id);
        canModifyUser(user, authentication);

        user.getFavorites().clear();
        user.getProducts().forEach(product -> {
            boolean anyActiveBooking = product.getBookings().stream().anyMatch(booking -> (booking.getCheckOut().isAfter(LocalDate.now()) || booking.getCheckOut().isEqual(LocalDate.now())));
            if(anyActiveBooking) throw new BadRequestException("You cannot delete the user as some of their products are currently booked.");
        });
        userRespository.deleteById(id);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest, Authentication authentication) {
        User user = existById(id);
        canModifyUser(user, authentication);

        String email = userRequest.email();
        User userByEmail = userRespository.findByEmail(email).orElse(null);
        if (userByEmail != null && !(userByEmail.getId().equals(id)))
            throw new BadRequestException("The email '" + email + "' is already registered");
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setEmail(userRequest.email());
        return userMapper.userToUserResponse(user);
    }

    @Override
    public void handleFav(Long productId, Authentication authentication) {
        User user = existById(authenticationUserService.getUserDetailsFromAuthentication(authentication).id());
        Product product = productoService.existById(productId);
        boolean isFav = user.getFavorites().contains(product);
        if (isFav) user.removeFav(product);
         else user.addFav(product);
        userRespository.save(user);
    }

    public User existById(Long id) {
        return userRespository.findById(id).orElseThrow(() ->
                new NotFoundException("User with id " + id + " not found"));
    }

    private void canModifyUser(User user, Authentication authentication) {
        UserDetailsSlim userDetails = authenticationUserService.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!user.getId().equals(userDetails.id()))
                throw new ForbiddenException("You cannot modify this user");
        }
    }
}
