package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Transactional(readOnly = true)
    Optional<Category> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.productsCount = c.productsCount + 1 WHERE c.id = :id")
    void incrementCount(@Param("id") Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Category c SET c.productsCount = c.productsCount - 1 WHERE c.id = :id")
    void decrementCount(@Param("id") Long id);
}
