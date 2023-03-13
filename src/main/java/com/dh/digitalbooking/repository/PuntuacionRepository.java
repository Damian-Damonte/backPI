package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Puntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PuntuacionRepository extends JpaRepository<Puntuacion, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Producto p SET p.promedioPuntuacion = "
            + "(SELECT AVG(punt.valor) * 2 FROM Puntuacion punt WHERE punt.producto.id = :productoId) "
            + "WHERE p.id = :productoId")
    int updatePromedioPuntuacionProducto(@Param("productoId") Long productoId);
}
