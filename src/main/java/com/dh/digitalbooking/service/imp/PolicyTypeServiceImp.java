package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import com.dh.digitalbooking.dto.policyType.PolicyTypeRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.PolicyType;
import com.dh.digitalbooking.mapper.PolicyTypeMapper;
import com.dh.digitalbooking.repository.PolicyTypeRepository;
import com.dh.digitalbooking.service.PolicyTypeService;
import com.dh.digitalbooking.service.PolicyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PolicyTypeServiceImp implements PolicyTypeService {
    private final PolicyTypeRepository policyTypeRepository;
    private final PolicyService policyService;
    private final PolicyTypeMapper policyTypeMapper;

    public PolicyTypeServiceImp(PolicyTypeRepository policyTypeRepository, PolicyService policyService, PolicyTypeMapper policyTypeMapper) {
        this.policyTypeRepository = policyTypeRepository;
        this.policyService = policyService;
        this.policyTypeMapper = policyTypeMapper;
    }

    @Override
    public List<PolicyTypeFullDto> allPoliciesTypes() {
        return policyTypeRepository.findAll().stream().map(policyTypeMapper::policytypeToPolicyTypeFullDto).toList();
    }

    @Override
    public PolicyTypeFullDto getPolicieTypeById(Long id) {
        return policyTypeMapper.policytypeToPolicyTypeFullDto(existById(id));
    }

    @Override
    @Transactional
    public PolicyTypeFullDto savePolicyType(PolicyTypeRequest policyTypeRequest) {
        String name = policyTypeRequest.name();
        if(policyTypeRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a policy with the name '" + name + "'");
        PolicyType policyType = policyTypeRepository.save(PolicyType.builder().name(name).build());
        return policyTypeMapper.policytypeToPolicyTypeFullDto(policyType);
    }

    @Override
    @Transactional
    public void deletePolicyType(Long id) {
        existById(id);
        if(policyService.existsByPolicyType_id(id))
            throw new BadRequestException("You cannot delete the policy type with id " + id + " because there are policies registered with this type");
        policyTypeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PolicyTypeFullDto updatePolicyType(Long id, PolicyTypeRequest policyTypeRequest) {
        PolicyType policyType = existById(id);
        String name = policyTypeRequest.name();
        PolicyType policyTypeByNombre = policyTypeRepository.findByName(name).orElse(null);
        if(policyTypeByNombre != null && !(policyTypeByNombre.getId().equals(id)))
            throw new BadRequestException("There is already a policy with the name '" + name + "'");
        policyType.setName(name);
        return policyTypeMapper.policytypeToPolicyTypeFullDto(policyType);
    }

    @Override
    public PolicyType existById(Long id) {
        return policyTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Policy type with " + id + " not found"));
    }
}
