package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.model.Ciudad;
import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.projection.ProductoProjection;
import com.proyectoIntegrador.sprint1.repository.ProductoRepository;
import com.proyectoIntegrador.sprint1.service.CategoriaService;
import com.proyectoIntegrador.sprint1.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaServiceImp categoriaServiceImp;
    private final CiudadServiceImp ciudadServiceImp;

    public ProductoServiceImp(ProductoRepository productoRepository, CategoriaServiceImp categoriaServiceImp, CiudadServiceImp ciudadServiceImp) {
        this.productoRepository = productoRepository;
        this.categoriaServiceImp = categoriaServiceImp;
        this.ciudadServiceImp = ciudadServiceImp;
    }

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public List<ProductoProjection> getAllProductoReduced() {
        return productoRepository.findAllBy();
    }

    @Override
    public Producto getProductoById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Producto saveProducto(Producto producto) {
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
        emptyTitleValidation(producto);
        emptyCategoriaValidation(producto);
        emptyCiudadValidation(producto);

        Ciudad ciudad = ciudadServiceImp.existByIdValidation(producto.getCiudad().getId());
        Categoria categoria = categoriaServiceImp.existByIdValidation(producto.getCategoria().getId());
        producto.setCiudad(ciudad);
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    private Producto existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id del producto");
        return productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto con id " + id + " no encontrado"));
    }

    private void emptyTitleValidation (Producto producto){
        String titulo = producto.getTitulo();
        if( titulo == null || titulo.equals(""))
            throw new BadRequestException("El producto debe contener un titulo");
    }

    private void emptyCategoriaValidation(Producto producto) {
        if(producto.getCategoria() == null)
            throw new BadRequestException("El producto debe contener una categoria");
    }

    private void emptyCiudadValidation(Producto producto) {
        if(producto.getCiudad() == null)
            throw new BadRequestException("El producto debe contener una ciudad");
    }
}
