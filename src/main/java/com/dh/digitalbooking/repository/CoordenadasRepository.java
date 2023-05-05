package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Coordenadas;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CoordenadasRepository extends JpaRepository<Coordenadas, Long> {
}
