package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Amenity")
@Table(
        name = "amenities",
        uniqueConstraints = {
                @UniqueConstraint(name = "amenities_name_unique", columnNames = "name")
        }
)
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    @NotBlank(message = "La característica debe tener un name")
    @Size(max = 255, message = "El name de la característica no debe tener más de 255 caracteres")
    private String name;

    @ManyToMany(mappedBy = "amenities")
    @JsonIgnore
    private Set<Producto> products = new HashSet<>();

    public Amenity() {
    }

    public Amenity(String name) {
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

    public Set<Producto> getProducts() {
        return products;
    }

    public void setProducts(Set<Producto> products) {
        this.products = products;
    }
}
