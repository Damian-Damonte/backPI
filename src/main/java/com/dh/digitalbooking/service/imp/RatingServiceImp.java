package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;
import com.dh.digitalbooking.dto.user.UserDetailsSlim;
import com.dh.digitalbooking.entity.Rating;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Product;
import com.dh.digitalbooking.entity.User;
import com.dh.digitalbooking.mapper.RatingMapper;
import com.dh.digitalbooking.repository.RatingRepository;
import com.dh.digitalbooking.service.RatingService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RatingServiceImp implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductServiceImp productoServiceImp;
    private final RatingMapper ratingMapper;
    private final UserServiceImp userService;
    private final AuthenticationUserServiceImpl authenticationUserServiceImpl;

    public RatingServiceImp(RatingRepository ratingRepository, ProductServiceImp productoServiceImp, RatingMapper ratingMapper, UserServiceImp userService, AuthenticationUserServiceImpl authenticationUserServiceImpl) {
        this.ratingRepository = ratingRepository;
        this.productoServiceImp = productoServiceImp;
        this.ratingMapper = ratingMapper;
        this.userService = userService;
        this.authenticationUserServiceImpl = authenticationUserServiceImpl;
    }

    @Override
    public List<RatingFullDto> allRatings() {
        return ratingRepository.findAll().stream().map(ratingMapper::ratingToRatingFullDto).toList();
    }

    @Override
    public RatingFullDto getRatingById(Long id) {
        return ratingMapper.ratingToRatingFullDto(existByIdValidation(id));
    }

    @Override
    @Transactional
    public RatingFullDto saveRating(RatingRequest ratingRequest, Authentication authentication) {
        Product product = productoServiceImp.existByIdValidation(ratingRequest.product().id());
        User user = userService.existById(authenticationUserServiceImpl.getUserDetailsFromAuthentication(authentication).id());

        Rating rating = ratingRepository.save(Rating.builder()
                .value(ratingRequest.value())
                .user(user)
                .product(product)
                .build());

        user.getRatings().add(rating);
        ratingRepository.updateAVGRatingInProduct(product.getId());

        return ratingMapper.ratingToRatingFullDto(rating);
    }

    @Override
    @Transactional
    public void deleteRating(Long id, Authentication authentication) {
        Rating rating = existByIdValidation(id);
        UserDetailsSlim userDetails = authenticationUserServiceImpl.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!rating.getUser().getId().equals(userDetails.id()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }
        ratingRepository.deleteById(id);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
    }

    @Override
    @Transactional
    public RatingFullDto updateRating(Long id, RatingUpdate ratingUpdate, Authentication authentication) {
        Rating rating = existByIdValidation(id);
        UserDetailsSlim userDetails = authenticationUserServiceImpl.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!rating.getUser().getId().equals(userDetails.id()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }
        rating.setValue(ratingUpdate.value());
        ratingRepository.save(rating);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
        return ratingMapper.ratingToRatingFullDto(rating);
    }

    public Rating existByIdValidation(Long id) {
        return ratingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Rating with id " + id + " not found"));
    }
}
