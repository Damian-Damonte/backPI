package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("""
            SELECT CASE WHEN COUNT(b) = 0 THEN TRUE ELSE FALSE END
            FROM Booking b
            WHERE b.product.id = :productId
            AND ((b.checkIn <= :checkIn AND b.checkOut >= :checkIn) OR
            (b.checkIn <= :checkOut AND b.checkOut >= :checkOut) OR
            (b.checkIn >= :checkIn AND b.checkOut <= :checkOut))
        """)
    boolean existsBookingsByDates(@Param("productId") Long productId,
                                 @Param("checkIn") LocalDate checkIn,
                                 @Param("checkOut") LocalDate checkOut);

    List<Booking> getBookingByUserId(Long id);
}
