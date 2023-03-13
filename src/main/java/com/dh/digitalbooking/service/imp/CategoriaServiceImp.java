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
        String titulo = categoria.getTitulo();

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
        String updateTitulo = updateCategoria.getTitulo();
        Long id = updateCategoria.getId();
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
}
