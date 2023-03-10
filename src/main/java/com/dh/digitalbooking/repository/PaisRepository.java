package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PaisRepository extends JpaRepository<Pais, Long> {
    Optional<Pais> findByNombre (String nombre);
}
