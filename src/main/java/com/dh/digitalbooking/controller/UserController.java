package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.FavoritoDto;
import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationFacade authenticationFacade;


    public UserController(UserService userService, AuthenticationFacade authenticationFacade) {
        this.userService = userService;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.allUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDto> getUserById(
            @PathVariable("id") Long id,
            Authentication authentication) {
        UserDetailsDto userDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.ok(userService.getByIdUsuario(id, userDto));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "Al eliminar un usuario se eliminarán automaticamente las reservas y puntuaciones relacionadas a este"
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
            description = "Se pueden actualizar todos los atributos excepto la contraseña. No es necesario enviar las reservas, ya que de todas formas no se pueden actualizar a través del usuario."
    )
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateUsuario(id, userRequest));
    }

    @PostMapping("/favoritos")
    public ResponseEntity<Void> handleFav(@RequestBody @Valid FavoritoDto favoritoDto,
                                          Authentication authentication) {
        UserDetailsDto userDto = authenticationFacade.getUserInfo(authentication);
        userService.handleFav(favoritoDto, userDto);
        return ResponseEntity.noContent().build();
    }
}
