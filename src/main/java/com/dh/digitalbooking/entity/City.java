package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "City")
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(
            name = "country_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "cities_countries_fk")
    )
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private Set<Producto> products = new HashSet<>();

    public City() {
    }

    public City(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getPais() {
        return country;
    }

    public void setPais(Country country) {
        this.country = country;
    }

    public Set<Producto> getProducts() {
        return products;
    }

    public void setProducts(Set<Producto> productos) {
        this.products = productos;
    }
}
