package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;

import java.util.List;

public interface RatingService {
    List<RatingFullDto> allRatings();
    RatingFullDto getRatingById(Long id);
    RatingFullDto saveRating(RatingRequest ratingRequest, UserDetailsDto userDetailsDto);
    void deleteRating(Long id, UserDetailsDto userDetailsDto);
    RatingFullDto updateRating(Long id, RatingUpdate ratingUpdate, UserDetailsDto userDetailsDto);
}
