package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
