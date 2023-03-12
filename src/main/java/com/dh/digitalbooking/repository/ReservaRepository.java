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
    @Query("SELECT COUNT(*) FROM Reserva r " +
            "WHERE r.producto.id = :productoId " +
            "AND ((r.checkIn BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (r.checkOut BETWEEN :checkInDate AND :checkOutDate))")
    int countReservaByDates(
            @Param("productoId") Long productId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query("SELECT r FROM Reserva r " +
            "WHERE r.producto.id = :productoId " +
            "AND ((r.checkIn BETWEEN :checkInDate AND :checkOutDate) " +
            "OR (r.checkOut BETWEEN :checkInDate AND :checkOutDate))")
    Reserva findReservaByDates(
            @Param("productoId") Long productId,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );
}
