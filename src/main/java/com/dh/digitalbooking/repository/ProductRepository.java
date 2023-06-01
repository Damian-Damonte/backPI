package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p ORDER BY RAND() LIMIT 4")
    List<Product> findRandom();

    @Query(
            "SELECT p FROM Product p WHERE " +
                "(:cityId IS NULL OR p.city.id = :cityId) " +
                "AND (:categoriaId IS NULL OR p.category.id = :categoryId) " +
                "AND ((:checkIn IS NULL AND :checkOut IS NULL) OR p.id NOT IN " +
                "(SELECT r.product.id FROM Booking r WHERE " +
                    "(r.checkIn <= :checkOut AND r.checkOut >= :checkIn))) " +
                "ORDER BY p.id ASC"
    )
    Page<Product> findAllFilters(
            @Param("cityId") Long cityId,
            @Param("categoryId") Long categoryId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            Pageable pageable
    );

    boolean existsByCity_id(Long id);
}
