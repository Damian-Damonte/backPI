package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDTO;
import com.dh.digitalbooking.dto.policyType.PolicyTypeNoIdDTO;
import com.dh.digitalbooking.service.PolicyTypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/policy-type")
public class PolicyTypeController {
    private final PolicyTypeService policyTypeService;

    public PolicyTypeController(PolicyTypeService policyTypeService) {
        this.policyTypeService = policyTypeService;
    }

    @GetMapping
    public ResponseEntity<List<PolicyTypeFullDTO>> getAllPoliciesTypes() {
        return ResponseEntity.ok(policyTypeService.allPoliciesTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyTypeFullDTO> getPolicyTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyTypeService.getPolicieTypeById(id));
    }

    @PostMapping
    public ResponseEntity<PolicyTypeFullDTO> savePolicyType(@RequestBody @Valid PolicyTypeNoIdDTO policyTypeNoIdDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                policyTypeService.savePolicyType(policyTypeNoIdDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicyType(@PathVariable Long id) {
        policyTypeService.deletePolicyType(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<PolicyTypeFullDTO> updatePolicyType(@RequestBody @Valid PolicyTypeFullDTO policyTypeFullDTO) {
        return ResponseEntity.ok(policyTypeService.updatePolicyType(policyTypeFullDTO));
    }
}
