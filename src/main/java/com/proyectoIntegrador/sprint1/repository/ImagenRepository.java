package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {
}
