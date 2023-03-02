package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.TipoPolitica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface TipoPoliticaRepository extends JpaRepository<TipoPolitica, Long> {
    Optional<TipoPolitica> findByNombre(String nombre);
}
