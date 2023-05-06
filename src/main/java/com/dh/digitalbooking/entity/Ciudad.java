package com.dh.digitalbooking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Ciudad")
@Table(name = "ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(
            name = "pais_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "ciudades_paises_fk")
    )
    private Country country;

    @OneToMany(mappedBy = "ciudad")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    public Ciudad() {
    }

    public Ciudad(String nombre, Country country) {
        this.nombre = nombre;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Country getPais() {
        return country;
    }

    public void setPais(Country country) {
        this.country = country;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}