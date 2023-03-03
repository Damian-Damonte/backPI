package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Politica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticaRepository extends JpaRepository<Politica, Long> {
}
