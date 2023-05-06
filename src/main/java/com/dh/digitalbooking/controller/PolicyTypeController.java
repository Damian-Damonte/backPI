package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.entity.PolicyType;
import com.dh.digitalbooking.service.PolicyTypeService;
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
    public ResponseEntity<List<PolicyType>> getAllPoliciesTypes() {
        return ResponseEntity.ok(policyTypeService.allPoliciesTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyType> getPolicyTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyTypeService.getPolicieTypeById(id));
    }

    @PostMapping
    public ResponseEntity<PolicyType> savePolicieType(@RequestBody PolicyType policyType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                policyTypeService.savePolicyType(policyType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicieType(@PathVariable Long id) {
        policyTypeService.deletePolicyType(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<PolicyType> updatePolicieType(@RequestBody PolicyType policyType) {
        return ResponseEntity.ok(policyTypeService.updatePolicyType(policyType));
    }
}
