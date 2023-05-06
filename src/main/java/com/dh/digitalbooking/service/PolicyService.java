package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.Policy;

public interface PolicyService {
    Policy getPolicyById(Long id);
    boolean existsByPolicyType_id(Long id);
}
