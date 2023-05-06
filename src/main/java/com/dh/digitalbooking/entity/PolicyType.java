package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "PolicyType")
@Table(
        name = "policies_types",
        uniqueConstraints = {
                @UniqueConstraint(name = "policy_type_name_unique", columnNames = "name")
        }
)
public class PolicyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", length = 100)
    @NotBlank(message = "Policy name requiered")
    @Size(max = 255, message = "Policy name cannot be longer than 255 characters")
    private String name;

    @OneToMany(mappedBy = "policyType")
    @JsonIgnore
    Set<Politica> politicas = new HashSet<>();

    public PolicyType() {
    }

    public PolicyType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Politica> getPoliticas() {
        return politicas;
    }

    public void setPoliticas(Set<Politica> politicas) {
        this.politicas = politicas;
    }
}