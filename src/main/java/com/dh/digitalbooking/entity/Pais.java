package com.dh.digitalbooking.entity;

import jakarta.persistence.*;
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
    private String nombre;

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
}
