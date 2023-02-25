package com.proyectoIntegrador.sprint1.repository;

import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.projection.ProductoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<ProductoProjection> findAllBy();
}
