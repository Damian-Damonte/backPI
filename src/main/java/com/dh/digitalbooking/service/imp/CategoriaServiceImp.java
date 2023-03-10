package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Categoria;
import com.dh.digitalbooking.repository.CategoriaRepository;
import com.dh.digitalbooking.service.CategoriaService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class CategoriaServiceImp implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> allCategoria() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Categoria saveCategoria(Categoria categoria) {
        emptyAttributesValidation(categoria);
        String titulo = categoria.getTitulo();
        titleLengthValidation(titulo);

        if (categoriaRepository.findByTitulo(titulo).isPresent())
            throw new BadRequestException("Ya existe una categoria con el titulo '" + titulo + "'");

        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        Categoria categoria = existByIdValidation(id);
        if (!(categoria.getProductos().isEmpty()))
            throw new BadRequestException("No puede eliminar la categoria con id " + id + " ya que hay productos de dicha categoria");

        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria updateCategoria(Categoria updateCategoria) {
        emptyAttributesValidation(updateCategoria);

        String updateTitulo = updateCategoria.getTitulo();
        Long id = updateCategoria.getId();

        titleLengthValidation(updateTitulo);
        existByIdValidation(id);

        Categoria categoriaByTitulo = categoriaRepository.findByTitulo(updateTitulo).orElse(null);
        if (categoriaByTitulo != null && !(Objects.equals(categoriaByTitulo.getId(), id))) {
            throw new BadRequestException("Ya existe una categoria con el titulo '" + updateTitulo + "'");
        }

        return categoriaRepository.save(updateCategoria);
    }

    public Categoria existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id de la categoria");
        return categoriaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Categoria con id " + id + " no encontrada"));
    }

    private void titleLengthValidation(String titulo) {
        if (titulo.length() > 45)
            throw new BadRequestException("El titulo no debe tener más de 45 caracteres");
    }

    private void emptyAttributesValidation(Categoria categoria) {
        String titulo = categoria.getTitulo();
        String description = categoria.getDescripcion();
        String urlImage = categoria.getUrlImagen();

        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("La categoria debe contener un titulo");
        titleLengthValidation(titulo);
        if (description == null || description.equals(""))
            throw new BadRequestException("La categoria debe contener una descripcion");
        if (urlImage == null || urlImage.equals(""))
            throw new BadRequestException("La categoria debe contener una imagen");
    }
}
