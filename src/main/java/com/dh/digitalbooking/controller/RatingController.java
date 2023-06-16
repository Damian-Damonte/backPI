package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;
import com.dh.digitalbooking.service.RatingService;
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
    private final RatingService ratingService;

    public RatingController(RatingServiceImp ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ResponseEntity<List<RatingFullDto>> getAllRatings() {
        return ResponseEntity.ok(ratingService.allRatings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingFullDto> getRatingById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(ratingService.getRatingById(id));
    }

    @PostMapping
    public ResponseEntity<RatingFullDto> saveRating(
            @RequestBody @Valid RatingRequest ratingRequest,
            Authentication authentication
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ratingService.saveRating(ratingRequest, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable @Valid Long id, Authentication authentication) {
        ratingService.deleteRating(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingFullDto> updateRating(@PathVariable Long id,
                                               @RequestBody @Valid RatingUpdate ratingUpdate,
                                               Authentication authentication
    ) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingUpdate, authentication));
    }
}
