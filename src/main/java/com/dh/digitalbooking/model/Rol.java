package com.dh.digitalbooking.model;

import jakarta.persistence.*;
@Entity(name = "Rol")
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(name = "usuario_email_unique", columnNames = "nombre")
        })
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    public Rol() {
    }

    public Rol(String nombre) {
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
