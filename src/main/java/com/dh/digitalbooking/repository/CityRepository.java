package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface CityRepository extends JpaRepository<City, Long> {
    boolean existsByCountry_Id(@Param("countryId") Long countryId);
}
