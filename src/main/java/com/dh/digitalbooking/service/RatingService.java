package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Rating;

import java.util.List;

public interface RatingService {
    List<Rating> allRatings();
    Rating getRatingById(Long id);
    Rating saveRating(Rating rating, UserDetailsDto userDetailsDto);
    void deleteRating(Long id);
    Rating updateRating(Rating rating);
}
