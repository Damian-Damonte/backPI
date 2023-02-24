package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {
}
