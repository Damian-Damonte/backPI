package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Coordinates;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
}
