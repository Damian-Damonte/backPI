package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.PolicyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PolicyTypeRepository extends JpaRepository<PolicyType, Long> {
    Optional<PolicyType> findByName(String name);
}
