package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

@Entity(name = "Caracteristica")
@Table(
        name = "caracteristicas",
        uniqueConstraints = {
                @UniqueConstraint(name = "caracteristicas_nombre_unique", columnNames = "nombre")
        }
)
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Caracteristica() {
    }

    public Caracteristica(String nombre) {
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
