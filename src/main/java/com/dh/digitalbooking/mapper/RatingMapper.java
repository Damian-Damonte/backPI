package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.entity.Rating;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface RatingMapper {
    RatingFullDto ratingToRatingFullDto(Rating rating);
}
