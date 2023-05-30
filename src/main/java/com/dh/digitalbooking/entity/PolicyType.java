package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}