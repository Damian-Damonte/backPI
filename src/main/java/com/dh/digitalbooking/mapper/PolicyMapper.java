package com.dh.digitalbooking.mapper;

import com.dh.digitalbooking.dto.policy.PolicyFullDTO;
import com.dh.digitalbooking.dto.policy.PolicyPostDTO;
import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import com.dh.digitalbooking.entity.Policy;
import com.dh.digitalbooking.entity.PolicyType;
import org.springframework.stereotype.Component;

@Component
public class PolicyMapper {
    public PolicyFullDTO policyToPolicyFullDTO(Policy policy) {
        PolicyTypeFullDto policyTypeFullDTO = new PolicyTypeFullDto(
                policy.getPolicyType().getId(), policy.getPolicyType().getName());
        return new PolicyFullDTO(policy.getId(), policy.getDescription(), policyTypeFullDTO);
    }

    public Policy policyFullDTOToPolicy(PolicyFullDTO policyFullDTO) {
        PolicyType policyType = new PolicyType();
        policyType.setId(policyFullDTO.policyType().id());
        policyType.setName(policyFullDTO.policyType().name());

        Policy policy = new Policy();
        policy.setId(policyFullDTO.id());
        policy.setDescription(policyFullDTO.description());
        policy.setPolicyType(policyType);
        return  policy;
    }

    public Policy policyPostDTOToPolicy(PolicyPostDTO policyPostDTO) {
        PolicyType policyType = new PolicyType();
        policyType.setId(policyPostDTO.policyTypeId());

        Policy policy = new Policy();
        policy.setDescription(policyPostDTO.description());
        policy.setPolicyType(policyType);
        return policy;
    }
}
