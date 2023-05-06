package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDTO;
import com.dh.digitalbooking.dto.policyType.PolicyTypeNoIdDTO;
import com.dh.digitalbooking.entity.PolicyType;
import org.springframework.stereotype.Component;

@Component
public class PolicyTypeMapper {
    public PolicyTypeFullDTO policyTypeToPolicyTypeFullDTO(PolicyType policyType) {
        return new PolicyTypeFullDTO(policyType.getId(), policyType.getName());
    }

    public PolicyType policyTypeFullDTOToPolicyType(PolicyTypeFullDTO policyTypeFullDTO) {
        PolicyType policyType = new PolicyType();
        policyType.setId(policyTypeFullDTO.id());
        policyType.setName(policyType.getName());
        return policyType;
    }

    public PolicyType policyTypeNoIdToPolicyType(PolicyTypeNoIdDTO policyTypeNoIdDTO) {
        PolicyType policyType = new PolicyType();
        policyType.setName(policyTypeNoIdDTO.name());
        return policyType;
    }
}
