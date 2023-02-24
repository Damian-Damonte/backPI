package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.repository.CategoriaRepository;
import com.proyectoIntegrador.sprint1.service.CategoriaService;
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
        String titulo = categoria.getTitulo();
        String description = categoria.getDescripcion();
        String urlImage = categoria.getUrlImagen();

        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("La categoria debe contener un titulo");
        titleLengthValidation(titulo);
        if(description == null || description.equals(""))
            throw new BadRequestException("La categoria debe contener una descripcion");
        if(urlImage == null || urlImage.equals(""))
            throw new BadRequestException("La categoria debe contener una imagen");
        if (categoriaRepository.findByTitulo(titulo).isPresent())
            throw new BadRequestException("Ya existe una categoria con el titulo '" + titulo + "'");

        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteCategoria(Long id) {
        existByIdValidation(id);
        categoriaRepository.deleteById(id);
    }

    @Override
    public Categoria updateCategoria(Categoria updateCategoria) {
        Long id = updateCategoria.getId();
        String updateTitulo = updateCategoria.getTitulo();
        String updateDecription = updateCategoria.getDescripcion();
        String updateUrlImage = updateCategoria.getUrlImagen();
        Categoria currentCategoria = existByIdValidation(id);

        if(updateTitulo != null){
            titleLengthValidation(updateTitulo);

            Categoria categoriaByTitulo = categoriaRepository.findByTitulo(updateTitulo).orElse(null);
            if(categoriaByTitulo != null && !(Objects.equals(categoriaByTitulo.getId(), id))){
                throw  new BadRequestException("Ya existe una categoria con el titulo '" + updateTitulo + "'");
            }
        }

        if(updateTitulo != null && !updateTitulo.equals(""))
            currentCategoria.setTitulo(updateTitulo);
        if(updateDecription != null && !updateDecription.equals(""))
            currentCategoria.setDescripcion(updateDecription);
        if(updateUrlImage != null && !updateUrlImage.equals(""))
            currentCategoria.setUrlImagen(updateUrlImage);

        return categoriaRepository.save(currentCategoria);
    }

    private Categoria existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la categoria");
        return categoriaRepository.findById(id).orElseThrow(() ->
            new NotFoundException("Categoria con id " + id + " no encontrada"));
    }

    private void titleLengthValidation(String titulo) {
        if(titulo.length() > 45)
            throw new BadRequestException("El titulo no debe tener más de 45 caracteres");
    }
}
