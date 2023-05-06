package com.dh.digitalbooking.repository;

import com.dh.digitalbooking.entity.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    boolean existsByPolicyType_id(Long id);
}
