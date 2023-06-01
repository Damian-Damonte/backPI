package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.*;
import com.dh.digitalbooking.repository.ProductRepository;
import com.dh.digitalbooking.service.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryServiceImp categoriaServiceImp;
    private final CityService cityService;
    private final AmenityService amenityService;
    private final PolicyTypeService policyTypeService;
    private final ImageServiceImp imagenServiceImp;
    private final PolicyServiceImp politicaServiceImp;

    public ProductServiceImp(ProductRepository productRepository, CategoryServiceImp categoriaServiceImp, CityService cityService, AmenityService amenityService, PolicyTypeService policyTypeService, ImageServiceImp imagenServiceImp, PolicyServiceImp politicaServiceImp) {
        this.productRepository = productRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.cityService = cityService;
        this.amenityService = amenityService;
        this.policyTypeService = policyTypeService;
        this.imagenServiceImp = imagenServiceImp;
        this.politicaServiceImp = politicaServiceImp;
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
    public ProductPageDto getByAllFilters(int page, ProductoFilterRequest filters) {
        filtersValidations(filters);
        PageRequest pageRequest = PageRequest.ofSize(4).withPage(page);
        Page<Product> productoPage = productRepository.findAllFilters(
                filters.getCiudadId(),
                filters.getCategoriaId(),
                filters.getCheckIn(),
                filters.getCheckOut(),
                pageRequest
        );
        return toProductPageDto(productoPage);
    }

    @Override
    public List<Product> getRandomProductos() {
        return productRepository.findRandom();
    }

    @Override
    public Product getProductoById(Long id) {
        return existByIdValidation(id);
    }

    @Transactional
    @Override
    public Product saveProducto(Product product) {
        product.getImages().forEach(img -> img.setId(null));
        product.getPolicies().forEach(pol -> pol.setId(null));
        categoriaServiceImp.incrementCount(product.getCategory().getId());

        return getProducto(product);
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
    @Override
    public Product updateProducto(Product updateProduct) {
        Product product = existByIdValidation(updateProduct.getId());
        updateProduct.setAverageRating(product.getAverageRating());
        updateProduct.setBookings(product.getBookings());

        if (!(updateProduct.getCategory().getId().equals(product.getCategory().getId()))) {
            categoriaServiceImp.decrementCount(product.getCategory().getId());
            categoriaServiceImp.incrementCount(updateProduct.getCategory().getId());
        }

        return getProducto(updateProduct);
    }

    @Override
    public boolean existByCityId(Long id) {
        return productRepository.existsByCity_id(id);
    }

    private Product getProducto(Product product) {
        product.setCity(cityService.existByIdValidation(product.getCity().getId()));
        product.setCategory(categoriaServiceImp.existByIdValidation(product.getCategory().getId()));

        getCaracteristicas(product);
        getImagenes(product);
        getPoliticas(product);

        return productRepository.save(product);
    }

    private void getCaracteristicas(Product product) {
        Set<Amenity> amenities = new HashSet<>();
        product.getAmenities().forEach(car -> {
            Amenity currentCar = amenityService.existByIdValidation(car.getId());
            amenities.add(currentCar);
        });
        product.setAmenities(amenities);
    }

    private void getImagenes(Product product) {
        if (product.getImages().isEmpty())
            throw new BadRequestException("El product debe tener por lo menos una imagen");
        Long productoId = product.getId();
        Set<Image> imagenes = new HashSet<>();
        product.getImages().forEach(img -> {
            imagenValidation(productoId, img);
            img.setProduct(product);
            imagenes.add(img);
        });
        product.setImages(imagenes);
    }

    private void getPoliticas(Product product) {
        Long productoId = product.getId();
        Set<Policy> policies = new HashSet<>();
        product.getPolicies().forEach(politica -> {
            politicaValidation(productoId, politica);
            getTipoPolitica(politica);
            politica.setProduct(product);
            policies.add(politica);
        });
        product.setPolicies(policies);
    }

    private void getTipoPolitica(Policy policy) {
        Long tipoPoliticaId = policy.getPolicyType().getId();
        PolicyType policyType = policyTypeService.existById(tipoPoliticaId);

//        Ahora no se puede crear el tipo de policy cuando creamos un product
//        PolicyType policyType = tipoPoliticaId != null
//                ? tipoPoliticaServiceImp.existByIdValidation(tipoPoliticaId)
//                : tipoPoliticaServiceImp.savePolicyType(policy.getTipoPolitica());

        policy.setPolicyType(policyType);
    }

    public Product existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del product");
        return productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product con id " + id + " no encontrado"));
    }

    private void imagenValidation(Long productoId, Image image) {
        Long id = image.getId();
        if (id != null) {
            Image currentImg = imagenServiceImp.getImageById(id);
            if (!(currentImg.getProduct().getId().equals(productoId)))
                throw new BadRequestException("La image con id " + id + " no pertenece a este product");
        }
    }

    private void politicaValidation(Long productoId, Policy policy) {
        Long id = policy.getId();
        if (id != null) {
            Policy currentPolicy = politicaServiceImp.getPolicyById(id);
            if (!(currentPolicy.getProduct().getId().equals(productoId)))
                throw new BadRequestException("La policy con id " + id + " no pertenece a este product");
        }
    }

    private void filtersValidations(ProductoFilterRequest filters) {
        Long ciudadId = filters.getCiudadId();
        Long categoriaId = filters.getCategoriaId();
        LocalDate checkIn = filters.getCheckIn();
        LocalDate checkOut = filters.getCheckOut();

        if (ciudadId != null)
            cityService.existByIdValidation(ciudadId);
        if (categoriaId != null)
            categoriaServiceImp.existByIdValidation(categoriaId);
        if (checkIn != null)
            notPastDate(checkIn);
        if (checkOut != null)
            notPastDate(checkOut);
        if (checkIn != null && checkOut != null)
            datesValidation(checkIn, checkOut);
    }

    private void datesValidation(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn.isAfter(checkOut))
            throw new BadRequestException("La fecha de ingreso deber anterior a la fecha de finalización");
    }

    private void notPastDate(LocalDate date) {
        if (date.isBefore(LocalDate.now()))
            throw new BadRequestException("Las fechas fechas no deben ser anterior al día actual");
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
