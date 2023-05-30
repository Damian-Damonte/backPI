package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.policyType.PolicyTypeFullDto;
import com.dh.digitalbooking.dto.policyType.PolicyTypeRequest;
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
    public ResponseEntity<List<PolicyTypeFullDto>> getAllPoliciesTypes() {
        return ResponseEntity.ok(policyTypeService.allPoliciesTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyTypeFullDto> getPolicyTypeById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(policyTypeService.getPolicieTypeById(id));
    }

    @PostMapping
    public ResponseEntity<PolicyTypeFullDto> savePolicyType(@RequestBody @Valid PolicyTypeRequest policyTypeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                policyTypeService.savePolicyType(policyTypeRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicyType(@PathVariable Long id) {
        policyTypeService.deletePolicyType(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PolicyTypeFullDto> updatePolicyType(@PathVariable Long id,
                                                  @RequestBody @Valid PolicyTypeRequest policyTypeRequest) {
        return ResponseEntity.ok(policyTypeService.updatePolicyType(id, policyTypeRequest));
    }
}
