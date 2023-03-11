package com.dh.digitalbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
@Entity(name = "Pais")
@Table(
        name = "paises",
        uniqueConstraints = {
                @UniqueConstraint(name = "paises_nombre_unique", columnNames = "nombre")
        }
)
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "nombre", nullable = false)
    @NotNull(message = "El pais debe contener un nombre")
    @Size(max = 45, message = "El nombre del pais no debe tener mas de 45 caracteres")
    private String nombre;

    @OneToMany(mappedBy = "pais")
    @JsonIgnore
    private Set<Ciudad> ciudades = new HashSet<>();

    public Pais() {
    }

    public Pais(String nombre) {
        this.nombre = nombre;
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

    public Set<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Set<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
