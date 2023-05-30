package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import com.dh.digitalbooking.dto.policyType.PolicyTypeRequest;
import com.dh.digitalbooking.entity.PolicyType;
import java.util.List;

public interface PolicyTypeService {
    List<PolicyTypeFullDto> allPoliciesTypes();
    PolicyTypeFullDto getPolicieTypeById(Long id);
    PolicyTypeFullDto savePolicyType(PolicyTypeRequest policyTypeRequest);
    void deletePolicyType(Long id);
    PolicyTypeFullDto updatePolicyType(Long id, PolicyTypeRequest policyTypeRequest);
    PolicyType existById(Long id);
}
