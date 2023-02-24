package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.repository.ProductoRepository;
import com.proyectoIntegrador.sprint1.service.CategoriaService;
import com.proyectoIntegrador.sprint1.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaServiceImp categoriaServiceImp;

    public ProductoServiceImp(ProductoRepository productoRepository, CategoriaServiceImp categoriaServiceImp) {
        this.productoRepository = productoRepository;
        this.categoriaServiceImp = categoriaServiceImp;
    }

    @Override
    public List<Producto> allProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getProductoById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        emptyTitleValidation(producto.getTitulo());

        Categoria categoria = categoriaServiceImp.existByIdValidation(producto.getCategoria().getId());
        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        existByIdValidation(id);
        productoRepository.deleteById(id);
    }

    @Override
    public Producto updateProducto(Producto updateProducto) {
        existByIdValidation(updateProducto.getId());
        emptyTitleValidation(updateProducto.getTitulo());

        Categoria categoria = categoriaServiceImp.existByIdValidation(updateProducto.getCategoria().getId());
        updateProducto.setCategoria(categoria);

        return productoRepository.save(updateProducto);
    }


    private Producto existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id del producto");
        return productoRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Producto con id " + id + " no encontrado"));
    }

    private void emptyTitleValidation (String titulo){
        if(titulo == null || titulo.equals(""))
            throw new BadRequestException("El producto debe contener un titulo");
    }
}
