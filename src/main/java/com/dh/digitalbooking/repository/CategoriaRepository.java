package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Transactional(readOnly = true)
    Optional<Categoria> findByTitulo (String titulo);

    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.cantProductos = c.cantProductos + 1 WHERE c.id = :id")
    void sumarProducto(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Categoria c SET c.cantProductos = c.cantProductos - 1 WHERE c.id = :id")
    void restarProducto(@Param("id") Long id);
}
