package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.product.id = :productId " +
            "AND ((b.checkIn <= :checkIn AND b.checkOut >= :checkIn) OR " +
            "(b.checkIn <= :checkOut AND b.checkOut >= :checkOut) OR " +
            "(b.checkIn >= :checkIn AND b.checkOut <= :checkOut))")
    Booking findBookingsByDates(@Param("productId") Long productId,
                                @Param("checkIn") LocalDate checkIn,
                                @Param("checkOut") LocalDate checkOut);

    @Query("SELECT COUNT(b) FROM Booking b WHERE b.product.id = :productId " +
            "AND ((b.checkIn <= :checkIn AND b.checkOut >= :checkIn) OR " +
            "(b.checkIn <= :checkOut AND b.checkOut >= :checkOut) OR " +
            "(b.checkIn >= :checkIn AND b.checkOut <= :checkOut))")
    int countBookingsByDates(@Param("productId") Long productId,
                             @Param("checkIn") LocalDate checkIn,
                             @Param("checkOut") LocalDate checkOut);
}
