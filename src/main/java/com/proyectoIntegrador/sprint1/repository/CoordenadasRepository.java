package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Coordenadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadasRepository extends JpaRepository<Coordenadas, Long> {
}
