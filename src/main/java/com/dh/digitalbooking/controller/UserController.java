package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.user.UserFullDto;
import com.dh.digitalbooking.dto.user.UserRequest;
import com.dh.digitalbooking.dto.user.UserResponse;
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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserFullDto> getUserById(@PathVariable("id") Long id, Authentication authentication) {
        return ResponseEntity.ok(userService.getUserById(id, authentication));
    }

    @DeleteMapping("/{id}")
    @Operation(
            description = "When deleting a user, their bookings and ratings will also be deleted"
    )
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, Authentication authentication) {
        userService.deleteUser(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
            description = "All attributes can be updated except for the password."
    )
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @RequestBody @Valid UserRequest userRequest,
                                                   Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest, authentication));
    }

    @PostMapping("/favorites/{productId}")
    public ResponseEntity<Void> handleFav(@PathVariable Long productId, Authentication authentication) {
        userService.handleFav(productId, authentication);
        return ResponseEntity.noContent().build();
    }
}
