package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.*;
import com.dh.digitalbooking.repository.ProductoRepository;
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
public class ProductoServiceImp implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoryServiceImp categoriaServiceImp;
    private final CityService cityService;
    private final AmenityService amenityService;
    private final PolicyTypeService policyTypeService;
    private final ImageServiceImp imagenServiceImp;
    private final PolicyServiceImp politicaServiceImp;

    public ProductoServiceImp(ProductoRepository productoRepository, CategoryServiceImp categoriaServiceImp, CityService cityService, AmenityService amenityService, PolicyTypeService policyTypeService, ImageServiceImp imagenServiceImp, PolicyServiceImp politicaServiceImp) {
        this.productoRepository = productoRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.cityService = cityService;
        this.amenityService = amenityService;
        this.policyTypeService = policyTypeService;
        this.imagenServiceImp = imagenServiceImp;
        this.politicaServiceImp = politicaServiceImp;
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Page<Producto> getAllPage(int page) {
        PageRequest pageRequest = PageRequest.ofSize(2).withPage(page);
        return productoRepository.findAll(pageRequest);
    }

    @Override
    public ProductPageDto getByAllFilters(int page, ProductoFilterRequest filters) {
        filtersValidations(filters);
        PageRequest pageRequest = PageRequest.ofSize(4).withPage(page);
        Page<Producto> productoPage = productoRepository.findAllFilters(
                filters.getCiudadId(),
                filters.getCategoriaId(),
                filters.getCheckIn(),
                filters.getCheckOut(),
                pageRequest
        );
        return toProductPageDto(productoPage);
    }

    @Override
    public List<Producto> getRandomProductos() {
        return productoRepository.findRandom();
    }

    @Override
    public Producto getProductoById(Long id) {
        return existByIdValidation(id);
    }

    @Transactional
    @Override
    public Producto saveProducto(Producto producto) {
        producto.getImagenes().forEach(img -> img.setId(null));
        producto.getPoliticas().forEach(pol -> pol.setId(null));
        categoriaServiceImp.incrementCount(producto.getCategoria().getId());

        return getProducto(producto);
    }

    @Transactional
    @Override
    public void deleteProducto(Long id) {
        Producto producto = existByIdValidation(id);
        if (!(producto.getBookings().isEmpty()))
            throw new BadRequestException("El producto con id " + id + " no puede ser eliminado ya que se encuentra reservado");

        Set<User> usuariosFav = producto.getFavoritos();
        usuariosFav.forEach(user -> user.removeFav(producto));

        categoriaServiceImp.decrementCount(producto.getCategoria().getId());
        productoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Producto updateProducto(Producto updateProducto) {
        Producto producto = existByIdValidation(updateProducto.getId());
        updateProducto.setPromedioPuntuacion(producto.getPromedioPuntuacion());
        updateProducto.setBookings(producto.getBookings());

        if (!(updateProducto.getCategoria().getId().equals(producto.getCategoria().getId()))) {
            categoriaServiceImp.decrementCount(producto.getCategoria().getId());
            categoriaServiceImp.incrementCount(updateProducto.getCategoria().getId());
        }

        return getProducto(updateProducto);
    }

    @Override
    public boolean existByCityId(Long id) {
        return productoRepository.existsByCity_id(id);
    }

    private Producto getProducto(Producto producto) {
        producto.setCiudad(cityService.existByIdValidation(producto.getCiudad().getId()));
        producto.setCategoria(categoriaServiceImp.existByIdValidation(producto.getCategoria().getId()));

        getCaracteristicas(producto);
        getImagenes(producto);
        getPoliticas(producto);

        return productoRepository.save(producto);
    }

    private void getCaracteristicas(Producto producto) {
        Set<Amenity> amenities = new HashSet<>();
        producto.getCaracteristicas().forEach(car -> {
            Amenity currentCar = amenityService.existByIdValidation(car.getId());
            amenities.add(currentCar);
        });
        producto.setCaracteristicas(amenities);
    }

    private void getImagenes(Producto producto) {
        if (producto.getImagenes().isEmpty())
            throw new BadRequestException("El producto debe tener por lo menos una imagen");
        Long productoId = producto.getId();
        Set<Image> imagenes = new HashSet<>();
        producto.getImagenes().forEach(img -> {
            imagenValidation(productoId, img);
            img.setProducto(producto);
            imagenes.add(img);
        });
        producto.setImagenes(imagenes);
    }

    private void getPoliticas(Producto producto) {
        Long productoId = producto.getId();
        Set<Policy> policies = new HashSet<>();
        producto.getPoliticas().forEach(politica -> {
            politicaValidation(productoId, politica);
            getTipoPolitica(politica);
            politica.setProduct(producto);
            policies.add(politica);
        });
        producto.setPoliticas(policies);
    }

    private void getTipoPolitica(Policy policy) {
        Long tipoPoliticaId = policy.getPolicyType().getId();
        PolicyType policyType = policyTypeService.existById(tipoPoliticaId);

//        Ahora no se puede crear el tipo de policy cuando creamos un producto
//        PolicyType policyType = tipoPoliticaId != null
//                ? tipoPoliticaServiceImp.existByIdValidation(tipoPoliticaId)
//                : tipoPoliticaServiceImp.savePolicyType(policy.getTipoPolitica());

        policy.setPolicyType(policyType);
    }

    public Producto existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del producto");
        return productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto con id " + id + " no encontrado"));
    }

    private void imagenValidation(Long productoId, Image image) {
        Long id = image.getId();
        if (id != null) {
            Image currentImg = imagenServiceImp.getImageById(id);
            if (!(currentImg.getProducto().getId().equals(productoId)))
                throw new BadRequestException("La image con id " + id + " no pertenece a este producto");
        }
    }

    private void politicaValidation(Long productoId, Policy policy) {
        Long id = policy.getId();
        if (id != null) {
            Policy currentPolicy = politicaServiceImp.getPolicyById(id);
            if (!(currentPolicy.getProduct().getId().equals(productoId)))
                throw new BadRequestException("La policy con id " + id + " no pertenece a este producto");
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

    private ProductPageDto toProductPageDto(Page<Producto> page) {
        ProductPageDto pageDto = new ProductPageDto();
        pageDto.setContent(page.getContent());
        pageDto.setTotalPages(page.getTotalPages());
        pageDto.setCurrentPage(page.getNumber());
        pageDto.setTotalElements(page.getTotalElements());
        return pageDto;
    }
}
