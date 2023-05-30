package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Policy;
import com.dh.digitalbooking.repository.PolicyRepository;
import com.dh.digitalbooking.service.PolicyService;
import org.springframework.stereotype.Service;

@Service
public class PolicyServiceImp implements PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyServiceImp(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Policy with id " + id + " not found")
        );
    }

    @Override
    public boolean existsByPolicyTypeId(Long id) {
        return policyRepository.existsByPolicyType_id(id);
    }
}
