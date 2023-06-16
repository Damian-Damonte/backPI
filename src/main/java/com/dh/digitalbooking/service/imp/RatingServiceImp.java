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
import com.dh.digitalbooking.service.AuthenticationUserService;
import com.dh.digitalbooking.service.ProductService;
import com.dh.digitalbooking.service.RatingService;
import com.dh.digitalbooking.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RatingServiceImp implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductService productoService;
    private final RatingMapper ratingMapper;
    private final UserService userService;
    private final AuthenticationUserService authenticationUserService;

    public RatingServiceImp(RatingRepository ratingRepository, ProductService productoService, RatingMapper ratingMapper, UserService userService, AuthenticationUserService authenticationUserService) {
        this.ratingRepository = ratingRepository;
        this.productoService = productoService;
        this.ratingMapper = ratingMapper;
        this.userService = userService;
        this.authenticationUserService = authenticationUserService;
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
        Product product = productoService.existById(ratingRequest.product().id());
        User user = userService.existById(authenticationUserService.getUserDetailsFromAuthentication(authentication).id());

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
        canModifyRating(rating, authentication);

        ratingRepository.deleteById(id);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
    }

    @Override
    @Transactional
    public RatingFullDto updateRating(Long id, RatingUpdate ratingUpdate, Authentication authentication) {
        Rating rating = existByIdValidation(id);
        canModifyRating(rating, authentication);

        rating.setValue(ratingUpdate.value());
        ratingRepository.save(rating);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
        return ratingMapper.ratingToRatingFullDto(rating);
    }

    public Rating existByIdValidation(Long id) {
        return ratingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Rating with id " + id + " not found"));
    }

    private void canModifyRating(Rating rating, Authentication authentication) {
        UserDetailsSlim userDetails = authenticationUserService.getUserDetailsFromAuthentication(authentication);
        if (!userDetails.role().equals("ROLE_ADMIN")) {
            if (!rating.getUser().getId().equals(userDetails.id()))
                throw new BadRequestException("You cannot modify this rating as it does not belong to you");
        }
    }
}
