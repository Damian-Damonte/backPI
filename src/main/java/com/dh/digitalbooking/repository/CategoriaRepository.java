package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByTitulo (String titulo);
}
