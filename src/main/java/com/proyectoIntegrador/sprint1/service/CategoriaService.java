package com.proyectoIntegrador.sprint1.service;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Categoria;
import com.proyectoIntegrador.sprint1.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    public List<Categoria> allCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria getCategoriaById(Long id) {
        return existByIdValidation(id);
    }

    public Categoria saveCategoria(Categoria categoria) {
        String titulo = categoria.getTitulo();
        if (titulo == null || titulo.equals(""))
            throw new BadRequestException("La categoria debe contener un titulo");
        if (categoriaRepository.findByTitulo(titulo).isPresent())
            throw new BadRequestException("Ya existe una categoria con el titulo '" + titulo + "'");
        return categoriaRepository.save(categoria);
    }

    public void deleteCategoria(Long id) {
        existByIdValidation(id);
        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Categoria updateCategoria) {
        Long id = updateCategoria.getId();
        String updateTitulo = updateCategoria.getTitulo();

        Categoria currentCategoria = existByIdValidation(id);

        Categoria categoriaByTitulo = categoriaRepository.findByTitulo(updateTitulo).orElse(null);
        if(categoriaByTitulo != null && !(Objects.equals(categoriaByTitulo.getId(), id))){
            throw  new BadRequestException("Ya existe una categoria con el titulo '" + updateTitulo + "'");
        }

        if (updateTitulo != null && !updateTitulo.equals(""))
            currentCategoria.setTitulo(updateTitulo);
        currentCategoria.setDescripcion(updateCategoria.getDescripcion());
        currentCategoria.setUrlImagen(updateCategoria.getUrlImagen());

        return categoriaRepository.save(currentCategoria);
    }

    private Categoria existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la categoria");
        return categoriaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Categoria con id " + id + " no encontrada"));
    }
}
