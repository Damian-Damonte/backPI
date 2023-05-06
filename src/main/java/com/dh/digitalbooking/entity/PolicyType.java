package com.dh.digitalbooking.entity;

import jakarta.persistence.*;

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
    private String name;

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
}