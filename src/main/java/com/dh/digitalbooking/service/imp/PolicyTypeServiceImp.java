package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.PolicyType;
import com.dh.digitalbooking.repository.PolicyTypeRepository;
import com.dh.digitalbooking.service.PolicyTypeService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyTypeServiceImp implements PolicyTypeService {
    private final PolicyTypeRepository policyTypeRepository;

    public PolicyTypeServiceImp(PolicyTypeRepository policyTypeRepository) {
        this.policyTypeRepository = policyTypeRepository;
    }

    @Override
    public List<PolicyType> allPoliciesTypes() {
        return policyTypeRepository.findAll();
    }

    @Override
    public PolicyType getPolicieTypeById(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public PolicyType savePolicyType(PolicyType policyType) {
        String name = policyType.getName();

        if(policyTypeRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a policy with the name '" + name + "'");

        return policyTypeRepository.save(policyType);
    }

    @Override
    public void deletePolicyType(Long id) {
        PolicyType policyType = existByIdValidation(id);
        if(!(policyType.getPoliticas().isEmpty()))
            throw new BadRequestException("You cannot delete the policy with id " + id + " because there are policies registered with this type");
        policyTypeRepository.deleteById(id);
    }

    @Override
    public PolicyType updatePolicyType(PolicyType policyType) {
        Long id = policyType.getId();
        String nombre = policyType.getName();
        existByIdValidation(id);

        PolicyType policyTypeByNombre = policyTypeRepository.findByName(nombre).orElse(null);
        if(policyTypeByNombre != null && !(policyTypeByNombre.getId().equals(id)))
            throw new BadRequestException("There is already a policy with the name '" + nombre + "'");

        return policyTypeRepository.save(policyType);
    }

    public PolicyType existByIdValidation(Long id) {
        return policyTypeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Tipo de politica con id " + id + " no encontrado"));
    }
}
