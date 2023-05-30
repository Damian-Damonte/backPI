package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.rating.RatingFullDto;
import com.dh.digitalbooking.dto.rating.RatingRequest;
import com.dh.digitalbooking.dto.rating.RatingUpdate;
import com.dh.digitalbooking.entity.Rating;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.entity.Usuario;
import com.dh.digitalbooking.mapper.RatingMapper;
import com.dh.digitalbooking.repository.RatingRepository;
import com.dh.digitalbooking.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class RatingServiceImp implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductoServiceImp productoServiceImp;
    private final RatingMapper ratingMapper;

    public RatingServiceImp(RatingRepository ratingRepository, ProductoServiceImp productoServiceImp, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.productoServiceImp = productoServiceImp;
        this.ratingMapper = ratingMapper;
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
    public RatingFullDto saveRating(RatingRequest ratingRequest, UserDetailsDto userDetailsDto) {
        Long userId = userDetailsDto.getUserId();
        Producto product = productoServiceImp.existByIdValidation(ratingRequest.product().id());
//      cambiar esto usando el builder cuando actualice Usuario
        Usuario user = new Usuario();
        user.setId(userId);

        Rating rating = ratingRepository.save(Rating.builder()
                .value(ratingRequest.value())
                .user(user)
                .product(product)
                .build());
        ratingRepository.updateAVGRatingInProduct(product.getId());
        return ratingMapper.ratingToRatingFullDto(rating);
    }

    @Override
    @Transactional
    public void deleteRating(Long id, UserDetailsDto userDetailsDto) {
        Rating rating = existByIdValidation(id);
        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!rating.getUser().getId().equals(userDetailsDto.getUserId()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }
        ratingRepository.deleteById(id);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
    }

    @Override
    @Transactional
    public RatingFullDto updateRating(Long id, RatingUpdate ratingUpdate, UserDetailsDto userDetailsDto) {
        Rating rating = existByIdValidation(id);
        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!rating.getUser().getId().equals(userDetailsDto.getUserId()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }
        rating.setValue(ratingUpdate.value());
        ratingRepository.save(rating);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
        return ratingMapper.ratingToRatingFullDto(rating);
    }

//    private Rating getRating(Rating rating) {
//        Producto product = productoServiceImp.existByIdValidation(rating.getProduct().getId());
//        Usuario user = usuarioServiceImp.existByIdValidation(rating.getUser().getId());
//        rating.setProduct(product);
//        rating.setUser(user);
//        Rating ratingSave = ratingRepository.save(rating);
//        ratingRepository.updateAVGRatingInProduct(product.getId());
//        return ratingSave;
//    }

    public Rating existByIdValidation(Long id) {
        return ratingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Rating with id " + id + " not found"));
    }
}
