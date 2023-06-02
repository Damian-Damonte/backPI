package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.product.*;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.*;
import com.dh.digitalbooking.mapper.ProductMapper;
import com.dh.digitalbooking.repository.ProductRepository;
import com.dh.digitalbooking.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImp categoriaServiceImp;
    private final CityService cityService;
    private final AmenityService amenityService;
    private final PolicyTypeService policyTypeService;
    private final ImageServiceImp imagenServiceImp;
    private final PolicyServiceImp policyService;
    private final ProductMapper productMapper;

    public ProductServiceImp(ProductRepository productRepository, CategoryServiceImp categoriaServiceImp, CityService cityService, AmenityService amenityService, PolicyTypeService policyTypeService, ImageServiceImp imagenServiceImp, PolicyServiceImp policyService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.cityService = cityService;
        this.amenityService = amenityService;
        this.policyTypeService = policyTypeService;
        this.imagenServiceImp = imagenServiceImp;
        this.policyService = policyService;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducto() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllPage(int page) {
        PageRequest pageRequest = PageRequest.ofSize(2).withPage(page);
        return productRepository.findAll(pageRequest);
    }

    @Override
    public ProductPageDto getByAllFilters(int page, Long cityId, Long categoryId, LocalDate checkIn, LocalDate checkout) {
        filtersValidations(cityId, categoryId, checkIn, checkout);
        PageRequest pageRequest = PageRequest.ofSize(4).withPage(page);
        Page<Product> productoPage = productRepository.findAllFilters(cityId, categoryId, checkIn, checkout, pageRequest);
        return toProductPageDto(productoPage);
    }

    @Override
    public List<Product> getRandomProductos() {
        return productRepository.findRandom();
    }

    @Override
    public ProductFullDto getProductoById(Long id) {
        return productMapper.productToProductFullDto(existByIdValidation(id));
    }

    @Override
    @Transactional
    public ProductResponse saveProducto(ProductRequest productRequest) {
        Product product = productMapper.productRequestToProduct(productRequest);
        product.setCategory(categoriaServiceImp.existByIdValidation(product.getCategory().getId()));
        product.setCity(cityService.existByIdValidation(product.getCity().getId()));
        product.getImages().forEach(image -> image.setProduct(product));
        product.setAmenities(product.getAmenities().stream().map(amenity ->
                amenityService.existByIdValidation(amenity.getId())).collect(Collectors.toSet()));
        product.setPolicies(product.getPolicies().stream().peek(policy -> {
            policy.setPolicyType(policyTypeService.existById(policy.getPolicyType().getId()));
            policy.setProduct(product);
        }).collect(Collectors.toSet()));
        product.setBookings(new HashSet<>());
        categoriaServiceImp.incrementCount(productRequest.category().id());
        return productMapper.productToProductResponse(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProducto(Long id) {
        Product product = existByIdValidation(id);
        if (!(product.getBookings().isEmpty()))
            throw new BadRequestException("El product con id " + id + " no puede ser eliminado ya que se encuentra reservado");

        Set<User> usuariosFav = product.getFavorites();
        usuariosFav.forEach(user -> user.removeFav(product));

        categoriaServiceImp.decrementCount(product.getCategory().getId());
        productRepository.deleteById(id);
    }

    @Transactional
    public ProductResponse updateProducto(Long id, ProductUpdate productUpdateRequest) {
        Product product = existByIdValidation(id);
        Product productUpdate = productMapper.productUpdateToProduct(productUpdateRequest);
        productUpdate.setId(id);
        productUpdate.setCategory(categoriaServiceImp.existByIdValidation(productUpdate.getCategory().getId()));
        productUpdate.setCity(cityService.existByIdValidation(productUpdate.getCity().getId()));

        productUpdate.setAmenities(productUpdate.getAmenities().stream().map(amenity ->
                amenityService.existByIdValidation(amenity.getId())).collect(Collectors.toSet()));


        productUpdate.getImages().forEach(image -> {
            if(image.getId() != null) {
                Image saveImage = imagenServiceImp.getImageById(image.getId());
                if(!Objects.equals(saveImage.getProduct().getId(), id))
                    throw new BadRequestException("The image with id " + image.getId() + " not found in product with id " + id);
            }
            image.setProduct(productUpdate);
        });

        productUpdate.getPolicies().forEach(policy -> {
            if(policy.getId() != null) {
                Policy savePolicy = policyService.getPolicyById(policy.getId());
                if(!Objects.equals(savePolicy.getProduct().getId(), id))
                    throw new BadRequestException("The policy with id " + policy.getId() + " not found in product with id " + id);
            }
            policy.setProduct(productUpdate);
        });

        productUpdate.setAverageRating(product.getAverageRating());
        productUpdate.setBookings(product.getBookings());
        productUpdate.setRatings(product.getRatings());
        productUpdate.setFavorites(product.getFavorites());

        if (!(productUpdate.getCategory().getId().equals(product.getCategory().getId()))) {
            categoriaServiceImp.decrementCount(product.getCategory().getId());
            categoriaServiceImp.incrementCount(productUpdate.getCategory().getId());
        }

        return productMapper.productToProductResponse(productRepository.save(productUpdate));
    }

    @Override
    public boolean existByCityId(Long id) {
        return productRepository.existsByCity_id(id);
    }

    public Product existByIdValidation(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id " + id + " not found"));
    }

    private void filtersValidations(Long cityId, Long categoryId, LocalDate checkIn, LocalDate checkOut) {
        if (cityId != null)
            cityService.existByIdValidation(cityId);
        if (categoryId != null)
            categoriaServiceImp.existByIdValidation(categoryId);
        if(checkIn != null)
            notPastDate(checkIn);
        if(checkOut != null)
            notPastDate(checkOut);
        if (checkIn != null && checkOut != null && checkIn.isAfter(checkOut))
            throw new BadRequestException("The check-out date must be after the check-in date.");
    }

    private void notPastDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new BadRequestException("The dates must not be earlier than the current date");
    }

    private ProductPageDto toProductPageDto(Page<Product> page) {
        ProductPageDto pageDto = new ProductPageDto();
        pageDto.setContent(page.getContent());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setCurrentPage(page.getNumber());
        pageDto.setTotalElements(page.getTotalElements());
        return pageDto;
    }
}
