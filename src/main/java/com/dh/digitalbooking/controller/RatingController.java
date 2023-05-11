package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Rating;
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
    public ResponseEntity<List<Rating>> getAllRatings() {
        return ResponseEntity.ok(ratingServiceImp.allRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rating> getRatingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ratingServiceImp.getRatingById(id));
    }

    @PostMapping
    public ResponseEntity<Rating> saveRating(
            @RequestBody @Valid Rating rating,
            Authentication authentication
            ) {
        UserDetailsDto userDetailsDto = authenticationFacade.getUserInfo(authentication);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ratingServiceImp.saveRating(rating, userDetailsDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable @Valid Long id) {
        ratingServiceImp.deleteRating(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Rating> updateRating(@RequestBody @Valid Rating rating) {
        return ResponseEntity.ok(ratingServiceImp.updateRating(rating));
    }
}
