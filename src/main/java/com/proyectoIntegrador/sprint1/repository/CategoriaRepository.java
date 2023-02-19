package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo (String titulo);
}
