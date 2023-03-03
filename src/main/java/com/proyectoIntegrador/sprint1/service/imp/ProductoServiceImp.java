package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.*;
import com.proyectoIntegrador.sprint1.repository.ProductoRepository;
import com.proyectoIntegrador.sprint1.service.ProductoService;
import org.springframework.stereotype.Service;

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

    public ProductoServiceImp(ProductoRepository productoRepository, CategoriaServiceImp categoriaServiceImp, CiudadServiceImp ciudadServiceImp, CaracteristicaServiceImp caracteristicaServiceImp, TipoPoliticaServiceImp tipoPoliticaServiceImp, ImagenServiceImp imagenServiceImp) {
        this.productoRepository = productoRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.ciudadServiceImp = ciudadServiceImp;
        this.caracteristicaServiceImp = caracteristicaServiceImp;
        this.tipoPoliticaServiceImp = tipoPoliticaServiceImp;
        this.imagenServiceImp = imagenServiceImp;
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public List<Producto> getAllWithFilters(Long ciudadId, Long categoriaId) {
        return productoRepository.findAllWithFilters(ciudadId, categoriaId);
    }

    @Override
    public List<Producto> getRandomProductos() {
        return productoRepository.findRandom();
    }

    @Override
    public Producto getProductoById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        producto.getImagenes().forEach(img -> img.setId(null));

        return getProducto(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        existByIdValidation(id);
        productoRepository.deleteById(id);
    }

    @Override
    public Producto updateProducto(Producto updateProducto) {
        existByIdValidation(updateProducto.getId());
        return getProducto(updateProducto);
    }

    private Producto getProducto(Producto producto) {
        emptyAttributesValidation(producto);

        Ciudad ciudad = ciudadServiceImp.existByIdValidation(producto.getCiudad().getId());
        producto.setCiudad(ciudad);
        Categoria categoria = categoriaServiceImp.existByIdValidation(producto.getCategoria().getId());
        producto.setCategoria(categoria);

        Set<Caracteristica> caracteristicas = new HashSet<>();
        producto.getCaracteristicas().forEach(car -> {
            Caracteristica currentCar = caracteristicaServiceImp.existByIdValidation(car.getId());
            caracteristicas.add(currentCar);
        });
        producto.setCaracteristicas(caracteristicas);

        producto.getImagenes().forEach(this::imagenValidation);

        Set<Politica> politicas = new HashSet<>();
        producto.getPoliticas().forEach(politica -> {
            politicaValidation(politica);
            TipoPolitica tipoPolitica;

            if (politica.getTipoPolitica().getId() != null) {
                tipoPolitica = tipoPoliticaServiceImp.existByIdValidation(
                        politica.getTipoPolitica().getId());
            } else {
                tipoPolitica = tipoPoliticaServiceImp.saveTipoPolitica(
                        politica.getTipoPolitica());
            }

            politica.setTipoPolitica(tipoPolitica);
            politicas.add(politica);
        });
        producto.setPoliticas(politicas);

        if(producto.getCoordenadas() != null)
            coordenadasValidation(producto);

        return productoRepository.save(producto);
    }

    public Producto existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del producto");
        return productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto con id " + id + " no encontrado"));
    }

    private void emptyAttributesValidation(Producto producto) {
        String titulo = producto.getTitulo();
        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("El producto debe contener un titulo");
        if (producto.getCategoria() == null)
            throw new BadRequestException("El producto debe contener una categoria");
        if (producto.getCiudad() == null)
            throw new BadRequestException("El producto debe contener una ciudad");
    }

    private void emptyTitleValidation(Producto producto) {
        String titulo = producto.getTitulo();
        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("El producto debe contener un titulo");
    }

    private void emptyCategoriaValidation(Producto producto) {
        if (producto.getCategoria() == null)
            throw new BadRequestException("El producto debe contener una categoria");
    }

    private void emptyCiudadValidation(Producto producto) {
        if (producto.getCiudad() == null)
            throw new BadRequestException("El producto debe contener una ciudad");
    }

    private void imagenValidation(Imagen imagen) {
        Long id = imagen.getId();
        String titulo = imagen.getTitulo();
        String url = imagen.getUrl();
        if(titulo == null || titulo.equals(""))
            throw new BadRequestException("Las imagenes debe contener un titulo");
        if(url == null || url.equals(""))
            throw new BadRequestException("Las imagenes debe contener una url");
        if(id != null)
            imagenServiceImp.getByIdImagen(id);
    }

    private void politicaValidation(Politica politica) {
        String descripcion = politica.getDescripcion();
        TipoPolitica tipoPolitica = politica.getTipoPolitica();

        if(descripcion == null || descripcion.equals(""))
            throw new BadRequestException("Las politicas debe contener una descripcion");
        if(tipoPolitica == null)
            throw new BadRequestException("Las politicas debe contener un tipo de politica");
    }

    private void coordenadasValidation(Producto producto) {
        Coordenadas coordenadas = producto.getCoordenadas();
        if(coordenadas.getLongitud() == null)
            throw new BadRequestException("Las coordenadas debe contener la longitud");
        if(coordenadas.getLatitud() == null)
            throw new BadRequestException("Las coordenadas debe contener la latitud");
    }
}
