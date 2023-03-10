package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query(
            "SELECT p FROM Producto p WHERE (:ciudadId IS NULL OR p.ciudad.id = :ciudadId) AND " +
                    "(:categoriaId IS NULL OR p.categoria.id = :categoriaId)"
    )
    List<Producto> findAllWithFilters(
            @Param("ciudadId") Long ciudadId,
            @Param("categoriaId") Long categoriaId);

    @Query("SELECT p FROM Producto p ORDER BY RAND() LIMIT 4")
    List<Producto> findRandom();
}
