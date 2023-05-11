package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.entity.Rating;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Producto;
import com.dh.digitalbooking.entity.Usuario;
import com.dh.digitalbooking.repository.RatingRepository;
import com.dh.digitalbooking.service.RatingService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RatingServiceImp implements RatingService {
    private final RatingRepository ratingRepository;
    private final ProductoServiceImp productoServiceImp;
    private final UsuarioServiceImp usuarioServiceImp;

    public RatingServiceImp(RatingRepository ratingRepository, ProductoServiceImp productoServiceImp, UsuarioServiceImp usuarioServiceImp) {
        this.ratingRepository = ratingRepository;
        this.productoServiceImp = productoServiceImp;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Override
    public List<Rating> allRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Rating saveRating(Rating rating, UserDetailsDto userDetailsDto) {
        Long ratingUserId = rating.getUser().getId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!ratingUserId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("The provided user information does not match the currently authenticated user");
        }

        return getRating(rating);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = existByIdValidation(id);
        ratingRepository.deleteById(id);
        ratingRepository.updateAVGRatingInProduct(rating.getProduct().getId());
    }

    @Override
    public Rating updateRating(Rating rating) {
        existByIdValidation(rating.getId());
        return getRating(rating);
    }

    private Rating getRating(Rating rating) {
        Producto product = productoServiceImp.existByIdValidation(rating.getProduct().getId());
        Usuario user = usuarioServiceImp.existByIdValidation(rating.getUser().getId());
        rating.setProduct(product);
        rating.setUser(user);
        Rating ratingSave = ratingRepository.save(rating);
        ratingRepository.updateAVGRatingInProduct(product.getId());
        return ratingSave;
    }

    public Rating existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la puntuacion");
        return ratingRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Rating with id " + id + " not found"));
    }
}
