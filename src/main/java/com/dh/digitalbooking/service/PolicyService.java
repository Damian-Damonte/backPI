package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Policy;

public interface PolicyService {
    Policy getPolicyById(Long id);
    boolean existsByPolicyTypeId(Long id);
}
