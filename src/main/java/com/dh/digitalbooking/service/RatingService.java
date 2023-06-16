package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface RatingService {
    List<RatingFullDto> allRatings();
    RatingFullDto getRatingById(Long id);
    RatingFullDto saveRating(RatingRequest ratingRequest, Authentication authentication);
    void deleteRating(Long id, Authentication authentication);
    RatingFullDto updateRating(Long id, RatingUpdate ratingUpdate, Authentication authentication);
}
