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
@Entity(name = "Policy")
@Table(name = "policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT", length = 600)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "policty_type_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "policy_policy_type_fk")
    )
    private PolicyType policyType;

    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "products_policies_id_fk")
    )
    private Product product;
}
