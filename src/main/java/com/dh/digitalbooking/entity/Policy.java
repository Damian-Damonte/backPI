package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Column(name = "descripcion", nullable = false, columnDefinition = "TEXT", length = 600)
    private String description;

    @ManyToOne
    @JoinColumn(
            name = "tipo_politica_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "politicas_tipo_politicas_fk")
    )
    private PolicyType policyType;

    @ManyToOne
    @JoinColumn(
            name = "producto_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "productos_politicas_id_fk")
    )
    @JsonIgnore
    private Producto product;
}
