package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDTO;
import com.dh.digitalbooking.dto.policyType.PolicyTypeNoIdDTO;
import com.dh.digitalbooking.entity.PolicyType;
import java.util.List;

public interface PolicyTypeService {
    List<PolicyTypeFullDTO> allPoliciesTypes();
    PolicyTypeFullDTO getPolicieTypeById(Long id);
    PolicyTypeFullDTO savePolicyType(PolicyTypeNoIdDTO policyTypeNoIdDTO);
    void deletePolicyType(Long id);
    PolicyTypeFullDTO updatePolicyType(PolicyTypeFullDTO policyTypeFullDTO);
    PolicyType existByIdValidation(Long id);
}
