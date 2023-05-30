package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;
import com.dh.digitalbooking.security.AuthenticationFacade;
import com.dh.digitalbooking.service.imp.RatingServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    private final RatingServiceImp ratingServiceImp;
    private final AuthenticationFacade authenticationFacade;

    public RatingController(RatingServiceImp ratingServiceImp, AuthenticationFacade authenticationFacade) {
        this.ratingServiceImp = ratingServiceImp;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    public ResponseEntity<List<RatingFullDto>> getAllRatings() {
        return ResponseEntity.ok(ratingServiceImp.allRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingFullDto> getRatingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ratingServiceImp.getRatingById(id));
    }

    @PostMapping
    public ResponseEntity<RatingFullDto> saveRating(
            @RequestBody @Valid RatingRequest ratingRequest,
            Authentication authentication
            ) {
        UserDetailsDto userDetailsDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ratingServiceImp.saveRating(ratingRequest, userDetailsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable @Valid Long id, Authentication authentication) {
        UserDetailsDto userDetailsDto = authenticationFacade.getUserInfo(authentication);
        ratingServiceImp.deleteRating(id, userDetailsDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingFullDto> updateRating(@PathVariable Long id,
                                               @RequestBody @Valid RatingUpdate ratingUpdate,
                                               Authentication authentication
    ) {
        UserDetailsDto userDetailsDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.ok(ratingServiceImp.updateRating(id, ratingUpdate, userDetailsDto));
    }
}
