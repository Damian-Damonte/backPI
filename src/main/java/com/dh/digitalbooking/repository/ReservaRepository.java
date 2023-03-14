package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.producto.id = :productoId " +
            "AND ((r.checkIn <= :checkIn AND r.checkOut >= :checkIn) OR " +
            "(r.checkIn <= :checkOut AND r.checkOut >= :checkOut) OR " +
            "(r.checkIn >= :checkIn AND r.checkOut <= :checkOut))")
    Reserva findReservaByDates(@Param("productoId") Long productoId,
                               @Param("checkIn") LocalDate checkIn,
                               @Param("checkOut") LocalDate checkOut);

    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.producto.id = :productoId " +
            "AND ((r.checkIn <= :checkIn AND r.checkOut >= :checkIn) OR " +
            "(r.checkIn <= :checkOut AND r.checkOut >= :checkOut) OR " +
            "(r.checkIn >= :checkIn AND r.checkOut <= :checkOut))")
    int countReservasByDates(@Param("productoId") Long productoId,
                             @Param("checkIn") LocalDate checkIn,
                             @Param("checkOut") LocalDate checkOut);
}
