package com.dh.digitalbooking.service.imp;


import com.dh.digitalbooking.dto.ProductPageDto;
import com.dh.digitalbooking.dto.ProductoFilterRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.*;
import com.dh.digitalbooking.repository.ProductoRepository;
import com.dh.digitalbooking.service.ProductoService;
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
    private final CategoriaServiceImp categoriaServiceImp;
    private final CiudadServiceImp ciudadServiceImp;
    private final CaracteristicaServiceImp caracteristicaServiceImp;
    private final TipoPoliticaServiceImp tipoPoliticaServiceImp;
    private final ImagenServiceImp imagenServiceImp;
    private final PoliticaServiceImp politicaServiceImp;
    private final CoordenadasServiceImp coordenadasServiceImp;

    public ProductoServiceImp(ProductoRepository productoRepository, CategoriaServiceImp categoriaServiceImp, CiudadServiceImp ciudadServiceImp, CaracteristicaServiceImp caracteristicaServiceImp, TipoPoliticaServiceImp tipoPoliticaServiceImp, ImagenServiceImp imagenServiceImp, PoliticaServiceImp politicaServiceImp, CoordenadasServiceImp coordenadasServiceImp) {
        this.productoRepository = productoRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.ciudadServiceImp = ciudadServiceImp;
        this.caracteristicaServiceImp = caracteristicaServiceImp;
        this.tipoPoliticaServiceImp = tipoPoliticaServiceImp;
        this.imagenServiceImp = imagenServiceImp;
        this.politicaServiceImp = politicaServiceImp;
        this.coordenadasServiceImp = coordenadasServiceImp;
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Page<Producto> getAllPage(int page) {
        PageRequest pageRequest = PageRequest.ofSize(2).withPage(page);
        Page<Producto> productoPage = productoRepository.findAll(pageRequest);
        return productoPage;
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
        if (producto.getCoordenadas() != null)
            producto.getCoordenadas().setId(null);

        return getProducto(producto);
    }

    @Transactional
    @Override
    public void deleteProducto(Long id) {
        Producto producto = existByIdValidation(id);
        if (!(producto.getReservas().isEmpty()))
            throw new BadRequestException("El producto con id " + id + " no puede ser eliminado ya que se encuentra reservado");
        productoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Producto updateProducto(Producto updateProducto) {
        Producto producto = existByIdValidation(updateProducto.getId());
        updateProducto.setPromedioPuntuacion(producto.getPromedioPuntuacion());
        updateProducto.setReservas(producto.getReservas());
        return getProducto(updateProducto);
    }

    private Producto getProducto(Producto producto) {
        producto.setCiudad(ciudadServiceImp.existByIdValidation(producto.getCiudad().getId()));
        producto.setCategoria(categoriaServiceImp.existByIdValidation(producto.getCategoria().getId()));

        getCaracteristicas(producto);
        getImagenes(producto);
        getPoliticas(producto);
        if (producto.getCoordenadas() != null)
            getCoordenadas(producto);

        return productoRepository.save(producto);
    }

    private void getCaracteristicas(Producto producto) {
        Set<Caracteristica> caracteristicas = new HashSet<>();
        producto.getCaracteristicas().forEach(car -> {
            Caracteristica currentCar = caracteristicaServiceImp.existByIdValidation(car.getId());
            caracteristicas.add(currentCar);
        });
        producto.setCaracteristicas(caracteristicas);
    }

    private void getImagenes(Producto producto) {
        Long productoId = producto.getId();
        Set<Imagen> imagenes = new HashSet<>();
        producto.getImagenes().forEach(img -> {
            imagenValidation(productoId, img);
            img.setProducto(producto);
            imagenes.add(img);
        });
        producto.setImagenes(imagenes);
    }

    private void getPoliticas(Producto producto) {
        Long productoId = producto.getId();
        Set<Politica> politicas = new HashSet<>();
        producto.getPoliticas().forEach(politica -> {
            politicaValidation(productoId, politica);
            getTipoPolitica(politica);
            politica.setProducto(producto);
            politicas.add(politica);
        });
        producto.setPoliticas(politicas);
    }

    private void getTipoPolitica(Politica politica) {
        Long tipoPoliticaId = politica.getTipoPolitica().getId();

        TipoPolitica tipoPolitica = tipoPoliticaId != null
                ? tipoPoliticaServiceImp.existByIdValidation(tipoPoliticaId)
                : tipoPoliticaServiceImp.saveTipoPolitica(politica.getTipoPolitica());

        politica.setTipoPolitica(tipoPolitica);
    }

    public void getCoordenadas(Producto producto) {
        Long productoId = producto.getId();
        Coordenadas coordenadas = producto.getCoordenadas();
        coordenadasValidation(productoId, coordenadas);
        coordenadas.setProducto(producto);
        producto.setCoordenadas(coordenadas);
    }

    public Producto existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del producto");
        return productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto con id " + id + " no encontrado"));
    }

    private void imagenValidation(Long productoId, Imagen imagen) {
        Long id = imagen.getId();
        if (id != null) {
            Imagen currentImg = imagenServiceImp.getByIdImagen(id);
            if (!(currentImg.getProducto().getId().equals(productoId)))
                throw new BadRequestException("La imagen con id " + id + " no pertenece a este producto");
        }
    }

    private void politicaValidation(Long productoId, Politica politica) {
        Long id = politica.getId();
        if (id != null) {
            Politica currentPolitica = politicaServiceImp.getByIdPolitica(id);
            if (!(currentPolitica.getProducto().getId().equals(productoId)))
                throw new BadRequestException("La politica con id " + id + " no pertenece a este producto");
        }
    }

    private void coordenadasValidation(Long productoId, Coordenadas coordenadas) {
        Long id = coordenadas.getId();
        if (id != null) {
            Coordenadas currentCoordenadas = coordenadasServiceImp.getByIdCoordenadas(id);
            if (!(currentCoordenadas.getProducto().getId().equals(productoId)))
                throw new BadRequestException("Las coordenadas con id " + id + " no pertenecen a este producto");
        }
    }

    private void filtersValidations (ProductoFilterRequest filters) {
        Long ciudadId = filters.getCiudadId();
        Long categoriaId = filters.getCategoriaId();
        LocalDate checkIn = filters.getCheckIn();
        LocalDate checkOut = filters.getCheckOut();

        if (ciudadId != null)
            ciudadServiceImp.existByIdValidation(ciudadId);
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
        if(checkIn.isAfter(checkOut))
            throw new BadRequestException("La fecha de ingreso deber anterior a la fecha de finalización");
    }

    private void notPastDate (LocalDate date){
        if(date.isBefore(LocalDate.now()))
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
