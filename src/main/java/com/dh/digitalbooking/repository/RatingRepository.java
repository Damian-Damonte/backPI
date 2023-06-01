package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.averageRating = "
            + "(SELECT AVG(r.value) * 2 FROM Rating r WHERE r.product.id = :productId) "
            + "WHERE p.id = :productId")
    int updateAVGRatingInProduct(@Param("productId") Long productId);
}
