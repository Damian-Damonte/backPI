package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.*;
import com.proyectoIntegrador.sprint1.repository.ProductoRepository;
import com.proyectoIntegrador.sprint1.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public Producto saveProducto(Producto producto) {
        producto.getImagenes().forEach(img -> img.setId(null));
        producto.getPoliticas().forEach(pol -> pol.setId(null));
        if(producto.getCoordenadas() != null)
            producto.getCoordenadas().setId(null);

        return getProducto(producto);
    }

    @Transactional
    @Override
    public void deleteProducto(Long id) {
        existByIdValidation(id);
        productoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Producto updateProducto(Producto updateProducto) {
        existByIdValidation(updateProducto.getId());
        return getProducto(updateProducto);
    }

    private Producto getProducto(Producto producto) {
        emptyAttributesValidation(producto);

        producto.setCiudad(ciudadServiceImp.existByIdValidation(producto.getCiudad().getId()));
        producto.setCategoria(categoriaServiceImp.existByIdValidation(producto.getCategoria().getId()));

        getCaracteristicas(producto);
        getImagenes(producto);
        getPoliticas(producto);
        if(producto.getCoordenadas() != null)
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

    private void emptyAttributesValidation(Producto producto) {
        String titulo = producto.getTitulo();
        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("El producto debe contener un titulo");
        if (producto.getCategoria() == null)
            throw new BadRequestException("El producto debe contener una categoria");
        if (producto.getCiudad() == null)
            throw new BadRequestException("El producto debe contener una ciudad");
    }

    private void imagenValidation(Long productoId, Imagen imagen) {
        Long id = imagen.getId();
        String titulo = imagen.getTitulo();
        String url = imagen.getUrl();
        if(titulo == null || titulo.equals(""))
            throw new BadRequestException("Las imagenes debe contener un titulo");
        if(url == null || url.equals(""))
            throw new BadRequestException("Las imagenes debe contener una url");
        if(id != null){
            Imagen currentImg = imagenServiceImp.getByIdImagen(id);
            if(!(currentImg.getProducto().getId().equals(productoId)))
                throw new BadRequestException("La imagen con id " + id + " no pertenece a este producto");
        }
    }

    private void politicaValidation(Long productoId, Politica politica) {
        Long id = politica.getId();
        String descripcion = politica.getDescripcion();
        if(descripcion == null || descripcion.equals(""))
            throw new BadRequestException("Las politicas debe contener una descripcion");
        if(politica.getTipoPolitica() == null)
            throw new BadRequestException("Las politicas debe contener un tipo de politica");
        if(id != null){
            Politica currentPolitica = politicaServiceImp.getByIdPolitica(id);
            if(!(currentPolitica.getProducto().getId().equals(productoId)))
                throw new BadRequestException("La politica con id " + id + " no pertenece a este producto");
        }
    }

    private void coordenadasValidation(Long productoId, Coordenadas coordenadas) {
        Long id = coordenadas.getId();
        if(coordenadas.getLongitud() == null)
            throw new BadRequestException("Las coordenadas debe contener la longitud");
        if(coordenadas.getLatitud() == null)
            throw new BadRequestException("Las coordenadas debe contener la latitud");
        if(id != null) {
            Coordenadas currentCoordenadas = coordenadasServiceImp.getByIdCoordenadas(id);
            if(!(currentCoordenadas.getProducto().getId().equals(productoId)))
                throw new BadRequestException("Las coordenadas con id " + id + " no pertenecen a este producto");
        }
    }
}
