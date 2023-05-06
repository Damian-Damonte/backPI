package com.dh.digitalbooking.service;

import com.dh.digitalbooking.entity.PolicyType;
import java.util.List;

public interface PolicyTypeService {
    List<PolicyType> allPoliciesTypes();
    PolicyType getPolicieTypeById(Long id);
    PolicyType savePolicyType(PolicyType policyType);
    void deletePolicyType(Long id);
    PolicyType updatePolicyType(PolicyType policyType);
}
