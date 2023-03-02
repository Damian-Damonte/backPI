package com.proyectoIntegrador.sprint1.model;

import jakarta.persistence.*;

@Entity(name = "TipoPolitica")
@Table(
        name = "tipos_politicas",
        uniqueConstraints = {
                @UniqueConstraint(name = "tipo_politica_nombre_unique", columnNames = "nombre")
        }
)
public class TipoPolitica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    public TipoPolitica() {
    }

    public TipoPolitica(String nombre) {
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